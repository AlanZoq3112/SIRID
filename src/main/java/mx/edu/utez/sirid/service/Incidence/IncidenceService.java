package mx.edu.utez.sirid.service.Incidence;

import mx.edu.utez.sirid.model.Incidence.IIncidenceRepository;
import mx.edu.utez.sirid.model.Incidence.Incidence;
import mx.edu.utez.sirid.model.Incidence.Resources;
import mx.edu.utez.sirid.model.Incidence.ResourcesRepository;
import mx.edu.utez.sirid.model.Status.Status;
import mx.edu.utez.sirid.model.User.IUserRepository;
import mx.edu.utez.sirid.model.User.User;
import mx.edu.utez.sirid.utils.inserts.CustomResponse;
import mx.edu.utez.sirid.utils.messages.IncidenceMessage;
import mx.edu.utez.sirid.utils.messages.UserMessage;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class IncidenceService {
    @Autowired
    private IIncidenceRepository repository;

    @Autowired
    private ResourcesRepository resourcesRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private IUserRepository userRepository;

    @Value("${spring.os}")
    private String rootPath;

    private String separator = FileSystems.getDefault().getSeparator();
    private String BASEURL = "http://44.199.95.221:8090/api-sirid/incidence/loadfile/";

    public ResponseEntity<Resource> getImage(String uid) throws IOException {
        Path path = Paths.get(rootPath + separator + uid);
        ByteArrayResource resource = new ByteArrayResource(
                Files.readAllBytes(path)
        );
        //consulta ProductImages -> MimeType
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(resource);
    }

    //recuperar todas las incidenias
    @Transactional(readOnly = true)
    public CustomResponse<List<Incidence>> getAll() {
        return new CustomResponse<>(
                this.repository.findAll(),
                false, 200, "ok"
        );
    }

    //recuperar una sola incidencia
    @Transactional(readOnly = true)
    public CustomResponse<Incidence> getOne(Long id) {
        return new CustomResponse<>(
                this.repository.findById(id).get(),
                false, 200, "ok"
        );
    }

    //registrar una nueva incidencia
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Incidence> insert(Incidence incidence) {
        List<Resources> imagesList = incidence.getResourcesList();
        incidence.setResourcesList(null);
        Incidence  newIncidence=this.repository.save(incidence);
        Stream<Resources> resourcesStream= imagesList.stream().map(image -> {
            byte[] bytes = Base64.decodeBase64(image.getFilebase64());
            String uid = UUID.randomUUID().toString();
            System.out.println(image.getMimeType());
            image.setIncidence(newIncidence);
            image.setName(uid);
            try (OutputStream stream = new FileOutputStream(
                    rootPath + separator + uid + image.getMimeType())
            ) {
                stream.write(bytes);
                image.setUrl(BASEURL + uid + image.getMimeType());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return  image;
        });

       List<Resources>list=resourcesRepository.saveAllAndFlush(resourcesStream.collect(Collectors.toList()));
        newIncidence.setResourcesList(list);
        return new CustomResponse<>(
                newIncidence,
                false, 200,
                "Incidencia registrada correctamente"
        );
    }

    //actualizar una incidecnia
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Incidence> update(Incidence incidence) {
        if (!this.repository.existsById(incidence.getId()))
            return new CustomResponse<>(
                    null, true, 400,
                    "Esta incidecia no existe"
            );
        return new CustomResponse<>(
                this.repository.saveAndFlush(incidence),
                false, 200,
                "Incidencia registrada correctamente"
        );
    }

    //cambiar status de una incidencia
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Integer> changeStatus(Incidence incidence) {
        if (!this.repository.existsById(incidence.getId()))
            return new CustomResponse<>(
                    null, true, 400,
                    "Esta incidecia no existe"
            );
        return new CustomResponse<>(
                this.repository.updateStatusById(
                        incidence.getStatus(), incidence.getId()),
                false, 200,
                "Se modifico con exito el status de la incidencia"
        );
    }

    //Cambiar personal de soporte
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Integer> changePersonalSupport(Incidence incidence) throws MessagingException {
        System.out.println("159->"+incidence.getPersonalSoporte().getId());
        if (!this.repository.existsById(incidence.getId()))
            return new CustomResponse<>(null,true,400,"Esta incidencia no existe");

        Incidence Description = repository.findIncidence(incidence.getId());
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper= new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setTo(incidence.getPersonalSoporte().getCorreoElectronico());
        messageHelper.setFrom("soportetecnicoutezmorelos@gmail.com");
        messageHelper.setSubject("Se te ha asignado la incidencia "+incidence.getId()+":"+incidence.getTitle());
        IncidenceMessage message1 = new IncidenceMessage();
        String email= message1.newAssignment(incidence.getPersonalSoporte(),Description);
        messageHelper.setText(email,true);
        this.javaMailSender.send(mimeMessage);
        User support = this.userRepository.getById(incidence.getPersonalSoporte().getId());
        System.out.println(support.getName());

        messageHelper.setTo(Description.getDocente().getCorreoElectronico());
        messageHelper.setFrom("soportetecnicoutezmorelos@gmail.com");
        messageHelper.setSubject("Nueva actividad en la incidencia "+incidence.getId()+":"+incidence.getTitle());
        IncidenceMessage message2 = new IncidenceMessage();
        String email2= message2.newActivity(Description.getDocente(),incidence,support);
        messageHelper.setText(email2,true);
        this.javaMailSender.send(mimeMessage);

        return  new CustomResponse<>(
                this.repository.changePersonalSupport(incidence.getPersonalSoporte().getId(), incidence.getId()),
                false,200,"Personal de soporte modificado con exito"
                );
    }

    //finalizar una incidencia
    @Transactional(rollbackFor = {SQLException.class})
    public  CustomResponse<Integer> finalizeIncident(Incidence incidence) throws MessagingException {
        if(!this.repository.existsById(incidence.getId()))
            return new CustomResponse<>(
                    null,true,400,"Esta incidencia no existe"
            );
        Incidence description = repository.findIncidence(incidence.getId());
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper= new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setTo(description.getDocente().getCorreoElectronico());
        messageHelper.setFrom("soportetecnicoutezmorelos@gmail.com");
        messageHelper.setSubject("Se ha dado por concluida la incidencia:"+incidence.getTitle());
        IncidenceMessage message1 = new IncidenceMessage();
        String email= message1.finalizeIncident(description.getDocente(),description);
        messageHelper.setText(email,true);
        this.javaMailSender.send(mimeMessage);

        MimeMessage mimeMessage2=javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper2= new MimeMessageHelper(mimeMessage2, true, "UTF-8");
        messageHelper2.setTo(description.getPersonalSoporte().getCorreoElectronico());
        messageHelper2.setFrom("soportetecnicoutezmorelos@gmail.com");
        messageHelper2.setSubject("Se ha dado por concluida la incidencia:"+incidence.getTitle());
        IncidenceMessage message2 = new IncidenceMessage();
        String email2= message2.finalizeIncident(description.getPersonalSoporte(),description);
        messageHelper2.setText(email2,true);
        this.javaMailSender.send(mimeMessage2);
        return  new CustomResponse<>(
                this.repository.updateStatusById(incidence.getStatus(), incidence.getId()),
                false,200,"Se finalizo la incicencia: "+incidence.getTitle()
        );
    }

    //Historial de incidencias
    @Transactional(readOnly = true)
    public  CustomResponse<List<Incidence>> GetAllMyIncidences(User user){
        return  new CustomResponse<>(
                this.repository.findByPersonalSoporteOrDocente(user,user),
                false,200,"Historial de incidencias recuperado"
        );
    }

    //ver las incidencias en las que participa el personal de soporte de acuerdo a su status
    @Transactional(readOnly = true)
    public CustomResponse<List<Incidence>> lookIncidenceSupport(Status status,User soporte){
        System.out.println("incidence -> "+soporte.getCorreoElectronico());
        if ((!this.userRepository.existsByCorreoElectronico(soporte.getCorreoElectronico())))
        return new CustomResponse<>(
                null, true, 400,
                "Este usuario no existe"
        );

        return new CustomResponse<>(
                this.repository.lookIncidenceSupport(status,soporte),
                false,200,"Lista de incidencias recuperado con exito"
        );

    }

    //ver las incidencias en las que participa el docente de acuerdo a su status
    @Transactional(readOnly = true)
    public CustomResponse<List<Incidence>> lookIncidenceDocente(Status status,User docente){
        if ((!this.userRepository.existsByCorreoElectronico(docente.getCorreoElectronico())))
            return new CustomResponse<>(
                    null, true, 400,
                    "Este usuario no existe"
            );

        return new CustomResponse<>(
                this.repository.lookIncidenceTeacher(status, docente),
                false,200,"Lista de incidencias recuperado con exito"
        );

    }

    @Transactional(readOnly = true)
    public String newMessageTeacher(Incidence incidence) throws MessagingException {
        incidence = repository.getById(incidence.getId());
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper= new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setTo(incidence.getPersonalSoporte().getCorreoElectronico());
        messageHelper.setFrom("soportetecnicoutezmorelos@gmail.com");
        messageHelper.setSubject("Tienes nuevos mensajes por leer en la incidencia "+incidence.getId()+":"+incidence.getTitle());
        IncidenceMessage message1 = new IncidenceMessage();
        String email= message1.newMessagechat(incidence.getPersonalSoporte(),incidence);
        messageHelper.setText(email,true);
        this.javaMailSender.send(mimeMessage);

        return "Correo enviado con exito";
    }

    @Transactional(readOnly = true)
    public String newMessageSupport(Incidence incidence) throws MessagingException {
        incidence = repository.getById(incidence.getId());
        System.out.println(incidence.getTitle());
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper= new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setTo(incidence.getDocente().getCorreoElectronico());
        messageHelper.setFrom("soportetecnicoutezmorelos@gmail.com");
        messageHelper.setSubject("Tienes nuevos mensajes por leer en la incidencia "+incidence.getId()+":"+incidence.getTitle());
        IncidenceMessage message1 = new IncidenceMessage();
        String email= message1.newMessagechat(incidence.getDocente(),incidence);
        messageHelper.setText(email,true);
        this.javaMailSender.send(mimeMessage);

        return "Correo enviado con exito";
    }





    
}

