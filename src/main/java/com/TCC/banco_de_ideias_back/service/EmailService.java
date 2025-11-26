package com.TCC.banco_de_ideias_back.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void enviarEmailHtml(String destinatario, String assunto, String conteudoHtml) {
        try {
            MimeMessage mensagem = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensagem, true, "UTF-8");

            helper.setTo(destinatario);
            helper.setSubject(assunto);
            helper.setText(conteudoHtml, true); // TRUE → permite HTML

            javaMailSender.send(mensagem);

        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar email:", e);
        }
    }

    public String gerarEmailInteresse(
            String nomeProfessor,
            String nomeAluno,
            String cursoAluno,
            String tituloIdeia,
            String descricaoIdeia,
            String emailAluno,
            Long ideiaId
    ) {
        String template = """
        <h2 style="color:#1a237e;">📢 Novo interesse na sua ideia de TCC!</h2>
        <p>Olá, Professor <b>%s</b>!</p>
        <p>O aluno <b>%s</b> (%s) demonstrou interesse na sua ideia:</p>

        <div style="padding:12px;border-left:4px solid #1a237e;background:#f5f5f5;">
            <b>%s</b><br>
            <small>%s</small>
        </div>

        <p style="margin-top:16px;">O aluno está aguardando um retorno.</p>

        <a href="mailto:%s?subject=Sobre%%20a%%20sua%%20ideia&body=Olá%%20%s,"
           style="background:#0d6efd;color:#fff;padding:12px 20px;border-radius:6px;text-decoration:none;margin-right:10px;">
           Responder Aluno
        </a>

        <a href="http://127.0.0.1:5500/ideia.html?id=%d"
           style="background:#2e7d32;color:#fff;padding:12px 20px;border-radius:6px;text-decoration:none;">
           Ver Ideia na Plataforma
        </a>

        <p style="margin-top:30px;font-size:12px;color:#777;">Banco de Ideias — UFF</p>
    """;

        return template.formatted(
                nomeProfessor,
                nomeAluno,
                cursoAluno,
                tituloIdeia,
                descricaoIdeia,
                emailAluno,
                nomeAluno,
                ideiaId
        );
    }
}
