package mx.edu.utez.sirid.utils.messages;

import mx.edu.utez.sirid.model.Incidence.Incidence;
import mx.edu.utez.sirid.model.Role.Role;
import mx.edu.utez.sirid.model.User.IUserRepository;
import mx.edu.utez.sirid.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class IncidenceMessage {

    @Autowired
    private IUserRepository repository;
    @Autowired
    private JavaMailSender javaMailSender;

    String email;


    UserMessage message = new UserMessage();
    MimeMessageHelper messageHelper;

    public String newAssignment(User personalSupport,Incidence incidence) throws MessagingException {

        email="<!DOCTYPE html>\n" +
                "\n" +
                "<html lang=\"en\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">\n" +
                "\n" +
                "<head>\n" +
                "  <title></title>\n" +
                "  <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\n" +
                "  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\" />\n" +
                "  <!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]-->\n" +
                "  <style>\n" +
                "    * {\n" +
                "      box-sizing: border-box;\n" +
                "    }\n" +
                "\n" +
                "    body {\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "    }\n" +
                "\n" +
                "    a[x-apple-data-detectors] {\n" +
                "      color: inherit !important;\n" +
                "      text-decoration: inherit !important;\n" +
                "    }\n" +
                "\n" +
                "    #MessageViewBody a {\n" +
                "      color: inherit;\n" +
                "      text-decoration: none;\n" +
                "    }\n" +
                "\n" +
                "    p {\n" +
                "      line-height: inherit\n" +
                "    }\n" +
                "\n" +
                "    .desktop_hide,\n" +
                "    .desktop_hide table {\n" +
                "      mso-hide: all;\n" +
                "      display: none;\n" +
                "      max-height: 0px;\n" +
                "      overflow: hidden;\n" +
                "    }\n" +
                "\n" +
                "    .image_block img+div {\n" +
                "      display: none;\n" +
                "    }\n" +
                "\n" +
                "    @media (max-width:520px) {\n" +
                "      .desktop_hide table.icons-inner {\n" +
                "        display: inline-block !important;\n" +
                "      }\n" +
                "\n" +
                "      .icons-inner {\n" +
                "        text-align: center;\n" +
                "      }\n" +
                "\n" +
                "      .icons-inner td {\n" +
                "        margin: 0 auto;\n" +
                "      }\n" +
                "\n" +
                "      .image_block img.big,\n" +
                "      .row-content {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "\n" +
                "      .mobile_hide {\n" +
                "        display: none;\n" +
                "      }\n" +
                "\n" +
                "      .stack .column {\n" +
                "        width: 100%;\n" +
                "        display: block;\n" +
                "      }\n" +
                "\n" +
                "      .mobile_hide {\n" +
                "        min-height: 0;\n" +
                "        max-height: 0;\n" +
                "        max-width: 0;\n" +
                "        overflow: hidden;\n" +
                "        font-size: 0px;\n" +
                "      }\n" +
                "\n" +
                "      .desktop_hide,\n" +
                "      .desktop_hide table {\n" +
                "        display: table !important;\n" +
                "        max-height: none !important;\n" +
                "      }\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"background-color: #c9c9c9; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\"\n" +
                "       style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #c9c9c9;\" width=\"100%\">\n" +
                "  <tbody>\n" +
                "  <tr>\n" +
                "    <td>\n" +
                "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-1\"\n" +
                "             role=\"presentation\"\n" +
                "             style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #032d5d;\" width=\"100%\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                   class=\"row-content stack\" role=\"presentation\"\n" +
                "                   style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 500px;\"\n" +
                "                   width=\"500\">\n" +
                "              <tbody>\n" +
                "              <tr>\n" +
                "                <td class=\"column column-1\"\n" +
                "                    style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n" +
                "                    width=\"100%\">\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                         class=\"image_block block-1\" role=\"presentation\"\n" +
                "                         style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                "                         width=\"100%\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\"\n" +
                "                          style=\"width:100%;padding-right:0px;padding-left:0px;\">\n" +
                "                        <div align=\"center\" class=\"alignment\"\n" +
                "                             style=\"line-height:10px\"><img class=\"big\"\n" +
                "                                                           src=\"https://firebasestorage.googleapis.com/v0/b/carsibb-eb9b3.appspot.com/o/portada_3.png?alt=media&token=f9a952b3-88b1-4dd5-95ef-091649bf15b1\"\n" +
                "                                                           style=\"display: block; height: auto; border: 0; width: 500px; max-width: 100%;\"\n" +
                "                                                           width=\"500\" /></div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-2\"\n" +
                "             role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                   class=\"row-content stack\" role=\"presentation\"\n" +
                "                   style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; border-radius: 0; border-top: 0 dashed transparent; border-right: 0px dotted transparent; width: 500px;\"\n" +
                "                   width=\"500\">\n" +
                "              <tbody>\n" +
                "              <tr>\n" +
                "                <td class=\"column column-1\"\n" +
                "                    style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n" +
                "                    width=\"100%\">\n" +
                "                  <div class=\"spacer_block\"\n" +
                "                       style=\"height:60px;line-height:60px;font-size:1px;\"> </div>\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                         class=\"heading_block block-2\" role=\"presentation\"\n" +
                "                         style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                "                         width=\"100%\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\"\n" +
                "                          style=\"width:100%;text-align:center;padding-top:25px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n" +
                "                        <h2\n" +
                "                                style=\"margin: 0; color: #636365; font-size: 30px; font-family: Arial, Helvetica Neue, Helvetica, sans-serif; line-height: 120%; text-align: left; direction: ltr; font-weight: 700; letter-spacing: normal; margin-top: 0; margin-bottom: 0;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"tinyMce-placeholder\">¡Hola! se te ha asignado la incidencia : "+incidence.getTitle()+" </span></h2>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-3\"\n" +
                "             role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                   class=\"row-content stack\" role=\"presentation\"\n" +
                "                   style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 500px;\"\n" +
                "                   width=\"500\">\n" +
                "              <tbody>\n" +
                "              <tr>\n" +
                "                <td class=\"column column-1\"\n" +
                "                    style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-top: 5px; padding-bottom: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n" +
                "                    width=\"33.333333333333336%\">\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                         class=\"image_block block-1\" role=\"presentation\"\n" +
                "                         style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                "                         width=\"100%\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\"\n" +
                "                          style=\"width:100%;padding-right:0px;padding-left:0px;\">\n" +
                "                        <div align=\"center\" class=\"alignment\"\n" +
                "                             style=\"line-height:10px\"><img alt=\"I'm an image\"\n" +
                "                                                           src=\"https://cdn-icons-png.flaticon.com/512/149/149071.png\"\n" +
                "                                                           style=\"display: block; height: auto; border: 0; width: 92px; max-width: 100%;\"\n" +
                "                                                           title=\"I'm an image\" width=\"92\" /></div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "                <td class=\"column column-2\"\n" +
                "                    style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-top: 5px; padding-bottom: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n" +
                "                    width=\"66.66666666666667%\">\n" +
                "                  <table border=\"0\" cellpadding=\"10\" cellspacing=\"0\"\n" +
                "                         class=\"paragraph_block block-1\" role=\"presentation\"\n" +
                "                         style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\"\n" +
                "                         width=\"100%\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\">\n" +
                "                        <div\n" +
                "                                style=\"color:#101112;font-size:16px;font-family:'Helvetica Neue', Helvetica, Arial, sans-serif;font-weight:400;line-height:120%;text-align:left;direction:ltr;letter-spacing:0px;mso-line-height-alt:19.2px;\">\n" +
                "                          <p style=\"margin: 0;\"><strong>Asignacion de incidencia</strong></p>\n" +
                "                        </div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                  <table border=\"0\" cellpadding=\"10\" cellspacing=\"0\"\n" +
                "                         class=\"paragraph_block block-2\" role=\"presentation\"\n" +
                "                         style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\"\n" +
                "                         width=\"100%\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\">\n" +
                "                        <div\n" +
                "                                style=\"color:#101112;font-size:16px;font-family:Arial, Helvetica Neue, Helvetica, sans-serif;font-weight:400;line-height:120%;text-align:left;direction:ltr;letter-spacing:0px;mso-line-height-alt:19.2px;\">\n" +
                "                          <p style=\"margin: 0;\"> <strong>Se te ha asignado la incidencia:"+incidence.getTitle()+"</strong><br> " +
                "                               Estos son los detalles de la incidencia:<br><strong>"+incidence.getDescription()+"</strong></p>\n" +
                "                        </div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                         class=\"button_block block-3\" role=\"presentation\"\n" +
                "                         style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                "                         width=\"100%\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\"\n" +
                "                          style=\"text-align:left;padding-top:10px;padding-right:15px;padding-bottom:10px;padding-left:30px;\">\n" +
                "                           </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-4\"\n" +
                "             role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                   class=\"row-content stack\" role=\"presentation\"\n" +
                "                   style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; border-radius: 0; width: 500px;\"\n" +
                "                   width=\"500\">\n" +
                "              <tbody>\n" +
                "              <tr>\n" +
                "                <td class=\"column column-1\"\n" +
                "                    style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n" +
                "                    width=\"100%\">\n" +
                "                  <table border=\"0\" cellpadding=\"10\" cellspacing=\"0\"\n" +
                "                         class=\"divider_block block-1\" role=\"presentation\"\n" +
                "                         style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                "                         width=\"100%\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\">\n" +
                "                        <div align=\"center\" class=\"alignment\">\n" +
                "                          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                 role=\"presentation\"\n" +
                "                                 style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                "                                 width=\"100%\">\n" +
                "                            <tr>\n" +
                "                              <td class=\"divider_inner\"\n" +
                "                                  style=\"font-size: 1px; line-height: 1px; border-top: 1px solid #dddddd;\">\n" +
                "                                <span> </span></td>\n" +
                "                            </tr>\n" +
                "                          </table>\n" +
                "                        </div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                  <table border=\"0\" cellpadding=\"10\" cellspacing=\"0\"\n" +
                "                         class=\"paragraph_block block-2\" role=\"presentation\"\n" +
                "                         style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\"\n" +
                "                         width=\"100%\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\">\n" +
                "                        <div\n" +
                "                                style=\"color:#736c6c;font-size:9px;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;font-weight:400;line-height:120%;text-align:center;direction:ltr;letter-spacing:0px;mso-line-height-alt:10.799999999999999px;\">\n" +
                "                          <p style=\"margin: 0;\">SIRID: Sistema de Reporte de\n" +
                "                            Incidencias para Docentes</p>\n" +
                "                        </div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "  </tbody>\n" +
                "</table><!-- End -->\n" +
                "</body>\n" +
                "\n" +
                "</html>";
        return  email;

    }

    //Esta ya esta listo
    public String newActivity(User personalSupport,Incidence incidence) throws MessagingException {
        email="<!DOCTYPE html>\n" +
                "\n" +
                "<html lang=\"en\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">\n" +
                "\n" +
                "<head>\n" +
                "  <title></title>\n" +
                "  <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\n" +
                "  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\" />\n" +
                "  <!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]-->\n" +
                "  <style>\n" +
                "    * {\n" +
                "      box-sizing: border-box;\n" +
                "    }\n" +
                "\n" +
                "    body {\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "    }\n" +
                "\n" +
                "    a[x-apple-data-detectors] {\n" +
                "      color: inherit !important;\n" +
                "      text-decoration: inherit !important;\n" +
                "    }\n" +
                "\n" +
                "    #MessageViewBody a {\n" +
                "      color: inherit;\n" +
                "      text-decoration: none;\n" +
                "    }\n" +
                "\n" +
                "    p {\n" +
                "      line-height: inherit\n" +
                "    }\n" +
                "\n" +
                "    .desktop_hide,\n" +
                "    .desktop_hide table {\n" +
                "      mso-hide: all;\n" +
                "      display: none;\n" +
                "      max-height: 0px;\n" +
                "      overflow: hidden;\n" +
                "    }\n" +
                "\n" +
                "    .image_block img+div {\n" +
                "      display: none;\n" +
                "    }\n" +
                "\n" +
                "    @media (max-width:520px) {\n" +
                "      .desktop_hide table.icons-inner {\n" +
                "        display: inline-block !important;\n" +
                "      }\n" +
                "\n" +
                "      .icons-inner {\n" +
                "        text-align: center;\n" +
                "      }\n" +
                "\n" +
                "      .icons-inner td {\n" +
                "        margin: 0 auto;\n" +
                "      }\n" +
                "\n" +
                "      .image_block img.big,\n" +
                "      .row-content {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "\n" +
                "      .mobile_hide {\n" +
                "        display: none;\n" +
                "      }\n" +
                "\n" +
                "      .stack .column {\n" +
                "        width: 100%;\n" +
                "        display: block;\n" +
                "      }\n" +
                "\n" +
                "      .mobile_hide {\n" +
                "        min-height: 0;\n" +
                "        max-height: 0;\n" +
                "        max-width: 0;\n" +
                "        overflow: hidden;\n" +
                "        font-size: 0px;\n" +
                "      }\n" +
                "\n" +
                "      .desktop_hide,\n" +
                "      .desktop_hide table {\n" +
                "        display: table !important;\n" +
                "        max-height: none !important;\n" +
                "      }\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"background-color: #c9c9c9; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\"\n" +
                "       style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #c9c9c9;\" width=\"100%\">\n" +
                "  <tbody>\n" +
                "  <tr>\n" +
                "    <td>\n" +
                "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-1\"\n" +
                "             role=\"presentation\"\n" +
                "             style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #032d5d;\" width=\"100%\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                   class=\"row-content stack\" role=\"presentation\"\n" +
                "                   style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 500px;\"\n" +
                "                   width=\"500\">\n" +
                "              <tbody>\n" +
                "              <tr>\n" +
                "                <td class=\"column column-1\"\n" +
                "                    style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n" +
                "                    width=\"100%\">\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                         class=\"image_block block-1\" role=\"presentation\"\n" +
                "                         style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                "                         width=\"100%\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\"\n" +
                "                          style=\"width:100%;padding-right:0px;padding-left:0px;\">\n" +
                "                        <div align=\"center\" class=\"alignment\"\n" +
                "                             style=\"line-height:10px\"><img class=\"big\"\n" +
                "                                                           src=\"https://firebasestorage.googleapis.com/v0/b/carsibb-eb9b3.appspot.com/o/portada_3.png?alt=media&token=f9a952b3-88b1-4dd5-95ef-091649bf15b1\"\n" +
                "                                                           style=\"display: block; height: auto; border: 0; width: 500px; max-width: 100%;\"\n" +
                "                                                           width=\"500\" /></div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-2\"\n" +
                "             role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                   class=\"row-content stack\" role=\"presentation\"\n" +
                "                   style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; border-radius: 0; border-top: 0 dashed transparent; border-right: 0px dotted transparent; width: 500px;\"\n" +
                "                   width=\"500\">\n" +
                "              <tbody>\n" +
                "              <tr>\n" +
                "                <td class=\"column column-1\"\n" +
                "                    style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n" +
                "                    width=\"100%\">\n" +
                "                  <div class=\"spacer_block\"\n" +
                "                       style=\"height:60px;line-height:60px;font-size:1px;\"> </div>\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                         class=\"heading_block block-2\" role=\"presentation\"\n" +
                "                         style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                "                         width=\"100%\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\"\n" +
                "                          style=\"width:100%;text-align:center;padding-top:25px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n" +
                "                        <h2\n" +
                "                                style=\"margin: 0; color: #636365; font-size: 30px; font-family: Arial, Helvetica Neue, Helvetica, sans-serif; line-height: 120%; text-align: left; direction: ltr; font-weight: 700; letter-spacing: normal; margin-top: 0; margin-bottom: 0;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"tinyMce-placeholder\">¡Hola! "+personalSupport.getName()+" Tienes nueva actividad en tu incidencia: "+incidence.getTitle()+" </span></h2>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-3\"\n" +
                "             role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                   class=\"row-content stack\" role=\"presentation\"\n" +
                "                   style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 500px;\"\n" +
                "                   width=\"500\">\n" +
                "              <tbody>\n" +
                "              <tr>\n" +
                "                <td class=\"column column-1\"\n" +
                "                    style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-top: 5px; padding-bottom: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n" +
                "                    width=\"33.333333333333336%\">\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                         class=\"image_block block-1\" role=\"presentation\"\n" +
                "                         style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                "                         width=\"100%\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\"\n" +
                "                          style=\"width:100%;padding-right:0px;padding-left:0px;\">\n" +
                "                        <div align=\"center\" class=\"alignment\"\n" +
                "                             style=\"line-height:10px\"><img alt=\"I'm an image\"\n" +
                "                                                           src=\"https://cdn-icons-png.flaticon.com/512/149/149071.png\"\n" +
                "                                                           style=\"display: block; height: auto; border: 0; width: 92px; max-width: 100%;\"\n" +
                "                                                           title=\"I'm an image\" width=\"92\" /></div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "                <td class=\"column column-2\"\n" +
                "                    style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-top: 5px; padding-bottom: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n" +
                "                    width=\"66.66666666666667%\">\n" +
                "                  <table border=\"0\" cellpadding=\"10\" cellspacing=\"0\"\n" +
                "                         class=\"paragraph_block block-1\" role=\"presentation\"\n" +
                "                         style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\"\n" +
                "                         width=\"100%\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\">\n" +
                "                        <div\n" +
                "                                style=\"color:#101112;font-size:16px;font-family:'Helvetica Neue', Helvetica, Arial, sans-serif;font-weight:400;line-height:120%;text-align:left;direction:ltr;letter-spacing:0px;mso-line-height-alt:19.2px;\">\n" +
                "                          <p style=\"margin: 0;\"><strong>Asignacion de incidencia</strong></p>\n" +
                "                        </div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                  <table border=\"0\" cellpadding=\"10\" cellspacing=\"0\"\n" +
                "                         class=\"paragraph_block block-2\" role=\"presentation\"\n" +
                "                         style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\"\n" +
                "                         width=\"100%\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\">\n" +
                "                        <div\n" +
                "                                style=\"color:#101112;font-size:16px;font-family:Arial, Helvetica Neue, Helvetica, sans-serif;font-weight:400;line-height:120%;text-align:left;direction:ltr;letter-spacing:0px;mso-line-height-alt:19.2px;\">\n" +
                "                          <p style=\"margin: 0;\"> Ha cambiado el status de la incidencia:"+incidence.getTitle()+"<br> y ahora un personal de soporte se esta haciendo cargo de ella<br> " +
                "                               </p>\n" +
                "                        </div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                         class=\"button_block block-3\" role=\"presentation\"\n" +
                "                         style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                "                         width=\"100%\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\"\n" +
                "                          style=\"text-align:left;padding-top:10px;padding-right:15px;padding-bottom:10px;padding-left:30px;\">\n" +
                "                           </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-4\"\n" +
                "             role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                   class=\"row-content stack\" role=\"presentation\"\n" +
                "                   style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; border-radius: 0; width: 500px;\"\n" +
                "                   width=\"500\">\n" +
                "              <tbody>\n" +
                "              <tr>\n" +
                "                <td class=\"column column-1\"\n" +
                "                    style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n" +
                "                    width=\"100%\">\n" +
                "                  <table border=\"0\" cellpadding=\"10\" cellspacing=\"0\"\n" +
                "                         class=\"divider_block block-1\" role=\"presentation\"\n" +
                "                         style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                "                         width=\"100%\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\">\n" +
                "                        <div align=\"center\" class=\"alignment\">\n" +
                "                          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                 role=\"presentation\"\n" +
                "                                 style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                "                                 width=\"100%\">\n" +
                "                            <tr>\n" +
                "                              <td class=\"divider_inner\"\n" +
                "                                  style=\"font-size: 1px; line-height: 1px; border-top: 1px solid #dddddd;\">\n" +
                "                                <span> </span></td>\n" +
                "                            </tr>\n" +
                "                          </table>\n" +
                "                        </div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                  <table border=\"0\" cellpadding=\"10\" cellspacing=\"0\"\n" +
                "                         class=\"paragraph_block block-2\" role=\"presentation\"\n" +
                "                         style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\"\n" +
                "                         width=\"100%\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\">\n" +
                "                        <div\n" +
                "                                style=\"color:#736c6c;font-size:9px;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;font-weight:400;line-height:120%;text-align:center;direction:ltr;letter-spacing:0px;mso-line-height-alt:10.799999999999999px;\">\n" +
                "                          <p style=\"margin: 0;\">SIRID: Sistema de Reporte de\n" +
                "                            Incidencias para Docentes</p>\n" +
                "                        </div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "  </tbody>\n" +
                "</table><!-- End -->\n" +
                "</body>\n" +
                "\n" +
                "</html>";
        return email;

    }

    public String  finalizeIncident(User personalSupport,Incidence incidence) throws MessagingException {
        email="<!DOCTYPE html>\n" +
                "\n" +
                "<html lang=\"en\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">\n" +
                "\n" +
                "<head>\n" +
                "  <title></title>\n" +
                "  <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\n" +
                "  <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\" />\n" +
                "  <!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]-->\n" +
                "  <style>\n" +
                "    * {\n" +
                "      box-sizing: border-box;\n" +
                "    }\n" +
                "\n" +
                "    body {\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "    }\n" +
                "\n" +
                "    a[x-apple-data-detectors] {\n" +
                "      color: inherit !important;\n" +
                "      text-decoration: inherit !important;\n" +
                "    }\n" +
                "\n" +
                "    #MessageViewBody a {\n" +
                "      color: inherit;\n" +
                "      text-decoration: none;\n" +
                "    }\n" +
                "\n" +
                "    p {\n" +
                "      line-height: inherit\n" +
                "    }\n" +
                "\n" +
                "    .desktop_hide,\n" +
                "    .desktop_hide table {\n" +
                "      mso-hide: all;\n" +
                "      display: none;\n" +
                "      max-height: 0px;\n" +
                "      overflow: hidden;\n" +
                "    }\n" +
                "\n" +
                "    .image_block img+div {\n" +
                "      display: none;\n" +
                "    }\n" +
                "\n" +
                "    @media (max-width:520px) {\n" +
                "      .desktop_hide table.icons-inner {\n" +
                "        display: inline-block !important;\n" +
                "      }\n" +
                "\n" +
                "      .icons-inner {\n" +
                "        text-align: center;\n" +
                "      }\n" +
                "\n" +
                "      .icons-inner td {\n" +
                "        margin: 0 auto;\n" +
                "      }\n" +
                "\n" +
                "      .image_block img.big,\n" +
                "      .row-content {\n" +
                "        width: 100% !important;\n" +
                "      }\n" +
                "\n" +
                "      .mobile_hide {\n" +
                "        display: none;\n" +
                "      }\n" +
                "\n" +
                "      .stack .column {\n" +
                "        width: 100%;\n" +
                "        display: block;\n" +
                "      }\n" +
                "\n" +
                "      .mobile_hide {\n" +
                "        min-height: 0;\n" +
                "        max-height: 0;\n" +
                "        max-width: 0;\n" +
                "        overflow: hidden;\n" +
                "        font-size: 0px;\n" +
                "      }\n" +
                "\n" +
                "      .desktop_hide,\n" +
                "      .desktop_hide table {\n" +
                "        display: table !important;\n" +
                "        max-height: none !important;\n" +
                "      }\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"background-color: #c9c9c9; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\"\n" +
                "       style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #c9c9c9;\" width=\"100%\">\n" +
                "  <tbody>\n" +
                "  <tr>\n" +
                "    <td>\n" +
                "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-1\"\n" +
                "             role=\"presentation\"\n" +
                "             style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #032d5d;\" width=\"100%\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                   class=\"row-content stack\" role=\"presentation\"\n" +
                "                   style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 500px;\"\n" +
                "                   width=\"500\">\n" +
                "              <tbody>\n" +
                "              <tr>\n" +
                "                <td class=\"column column-1\"\n" +
                "                    style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n" +
                "                    width=\"100%\">\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                         class=\"image_block block-1\" role=\"presentation\"\n" +
                "                         style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                "                         width=\"100%\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\"\n" +
                "                          style=\"width:100%;padding-right:0px;padding-left:0px;\">\n" +
                "                        <div align=\"center\" class=\"alignment\"\n" +
                "                             style=\"line-height:10px\"><img class=\"big\"\n" +
                "                                                           src=\"https://firebasestorage.googleapis.com/v0/b/carsibb-eb9b3.appspot.com/o/portada_3.png?alt=media&token=f9a952b3-88b1-4dd5-95ef-091649bf15b1\"\n" +
                "                                                           style=\"display: block; height: auto; border: 0; width: 500px; max-width: 100%;\"\n" +
                "                                                           width=\"500\" /></div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-2\"\n" +
                "             role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                   class=\"row-content stack\" role=\"presentation\"\n" +
                "                   style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; border-radius: 0; border-top: 0 dashed transparent; border-right: 0px dotted transparent; width: 500px;\"\n" +
                "                   width=\"500\">\n" +
                "              <tbody>\n" +
                "              <tr>\n" +
                "                <td class=\"column column-1\"\n" +
                "                    style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n" +
                "                    width=\"100%\">\n" +
                "                  <div class=\"spacer_block\"\n" +
                "                       style=\"height:60px;line-height:60px;font-size:1px;\"> </div>\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                         class=\"heading_block block-2\" role=\"presentation\"\n" +
                "                         style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                "                         width=\"100%\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\"\n" +
                "                          style=\"width:100%;text-align:center;padding-top:25px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n" +
                "                        <h2\n" +
                "                                style=\"margin: 0; color: #636365; font-size: 30px; font-family: Arial, Helvetica Neue, Helvetica, sans-serif; line-height: 120%; text-align: left; direction: ltr; font-weight: 700; letter-spacing: normal; margin-top: 0; margin-bottom: 0;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"tinyMce-placeholder\">¡Hola! "+personalSupport.getName()+" La incidencia : "+incidence.getTitle()+" ha sido finalizada </span></h2>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-3\"\n" +
                "             role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                   class=\"row-content stack\" role=\"presentation\"\n" +
                "                   style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 500px;\"\n" +
                "                   width=\"500\">\n" +
                "              <tbody>\n" +
                "              <tr>\n" +
                "                <td class=\"column column-1\"\n" +
                "                    style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-top: 5px; padding-bottom: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n" +
                "                    width=\"33.333333333333336%\">\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                         class=\"image_block block-1\" role=\"presentation\"\n" +
                "                         style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                "                         width=\"100%\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\"\n" +
                "                          style=\"width:100%;padding-right:0px;padding-left:0px;\">\n" +
                "                        <div align=\"center\" class=\"alignment\"\n" +
                "                             style=\"line-height:10px\"><img alt=\"I'm an image\"\n" +
                "                                                           src=\"https://cdn-icons-png.flaticon.com/512/149/149071.png\"\n" +
                "                                                           style=\"display: block; height: auto; border: 0; width: 92px; max-width: 100%;\"\n" +
                "                                                           title=\"I'm an image\" width=\"92\" /></div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "                <td class=\"column column-2\"\n" +
                "                    style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-top: 5px; padding-bottom: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n" +
                "                    width=\"66.66666666666667%\">\n" +
                "                  <table border=\"0\" cellpadding=\"10\" cellspacing=\"0\"\n" +
                "                         class=\"paragraph_block block-1\" role=\"presentation\"\n" +
                "                         style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\"\n" +
                "                         width=\"100%\">\n" +
                "                    <tr>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                  <table border=\"0\" cellpadding=\"10\" cellspacing=\"0\"\n" +
                "                         class=\"paragraph_block block-2\" role=\"presentation\"\n" +
                "                         style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\"\n" +
                "                         width=\"100%\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\">\n" +
                "                        <div\n" +
                "                                style=\"color:#101112;font-size:16px;font-family:Arial, Helvetica Neue, Helvetica, sans-serif;font-weight:400;line-height:120%;text-align:left;direction:ltr;letter-spacing:0px;mso-line-height-alt:19.2px;\">\n" +
                "                          <p style=\"margin: 0;\"> <strong>Se ha finalizado la incidencia "+incidence.getTitle()+"</strong><br> </p>\n" +
                "                        </div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                         class=\"button_block block-3\" role=\"presentation\"\n" +
                "                         style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                "                         width=\"100%\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\"\n" +
                "                          style=\"text-align:left;padding-top:10px;padding-right:15px;padding-bottom:10px;padding-left:30px;\">\n" +
                "                           </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-4\"\n" +
                "             role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "          <td>\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                   class=\"row-content stack\" role=\"presentation\"\n" +
                "                   style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; border-radius: 0; width: 500px;\"\n" +
                "                   width=\"500\">\n" +
                "              <tbody>\n" +
                "              <tr>\n" +
                "                <td class=\"column column-1\"\n" +
                "                    style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\"\n" +
                "                    width=\"100%\">\n" +
                "                  <table border=\"0\" cellpadding=\"10\" cellspacing=\"0\"\n" +
                "                         class=\"divider_block block-1\" role=\"presentation\"\n" +
                "                         style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                "                         width=\"100%\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\">\n" +
                "                        <div align=\"center\" class=\"alignment\">\n" +
                "                          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                 role=\"presentation\"\n" +
                "                                 style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\"\n" +
                "                                 width=\"100%\">\n" +
                "                            <tr>\n" +
                "                              <td class=\"divider_inner\"\n" +
                "                                  style=\"font-size: 1px; line-height: 1px; border-top: 1px solid #dddddd;\">\n" +
                "                                <span> </span></td>\n" +
                "                            </tr>\n" +
                "                          </table>\n" +
                "                        </div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                  <table border=\"0\" cellpadding=\"10\" cellspacing=\"0\"\n" +
                "                         class=\"paragraph_block block-2\" role=\"presentation\"\n" +
                "                         style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\"\n" +
                "                         width=\"100%\">\n" +
                "                    <tr>\n" +
                "                      <td class=\"pad\">\n" +
                "                        <div\n" +
                "                                style=\"color:#736c6c;font-size:9px;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;font-weight:400;line-height:120%;text-align:center;direction:ltr;letter-spacing:0px;mso-line-height-alt:10.799999999999999px;\">\n" +
                "                          <p style=\"margin: 0;\">SIRID: Sistema de Reporte de\n" +
                "                            Incidencias para Docentes</p>\n" +
                "                        </div>\n" +
                "                      </td>\n" +
                "                    </tr>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              </tbody>\n" +
                "            </table>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "      </table>\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "  </tbody>\n" +
                "</table><!-- End -->\n" +
                "</body>\n" +
                "\n" +
                "</html>";
        return  email;

    }
}
