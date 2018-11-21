package ua.gmail.sydorenko.web.command;

import org.apache.log4j.Logger;
import ua.gmail.sydorenko.database.entity.Tariff;
import ua.gmail.sydorenko.database.exception.DaoSystemException;
import ua.gmail.sydorenko.util.PdfCreator;
import ua.gmail.sydorenko.web.Path;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Command for download a list of tariffs.
 *
 * @author M.Sydorenko
 */
public class DownloadCommand extends GeneralCommand {
    private static final long serialVersionUID = -5868243282075555305L;
    private static final Logger LOG = Logger.getLogger(DownloadCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoSystemException {
        LOG.debug("Command 'download tariff's list' starts");

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("tariffList") != null) {
            List<Tariff> tariffList = (List<Tariff>) session.getAttribute("tariffList");
            PdfCreator.downloadTariffs(tariffList);
        }
        LOG.debug("Command 'download tariff's list' finished");


        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "attachment; filename=" + "ViaTelecom.pdf");

        try (ServletOutputStream stream = response.getOutputStream()) {
            File fileToDownload = new File("D:\\ViaTelecom.pdf");
            FileInputStream input = new FileInputStream(fileToDownload);
            try (BufferedInputStream buf = new BufferedInputStream(input)) {
                int readBytes;
                while ((readBytes = buf.read()) != -1) {
                    stream.write(readBytes);
                }
            }
        } catch (IOException e) {
            LOG.error("Cannot download file");
        }
        return Path.PAGE_MAIN;
    }
}
