package dev.trabalhopicpay.infra;

import dev.trabalhopicpay.dtos.exception.ErrorResponseDTO;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.sql.SQLException;

@Provider
public class DataIntegrityViolationExceptionMapper implements ExceptionMapper<PersistenceException> {

    @Override
    public Response toResponse(PersistenceException exception) {
        String errorMessage = "Erro, integridade de dados.";

        if (exception.getCause() instanceof SQLException ) {
            if("23505".equals(((SQLException) exception.getCause()).getSQLState())) {
                String detailMessage = ((SQLException) exception.getCause()).getMessage();

                if (detailMessage.contains("tb_users_document_key")) {
                    errorMessage = "Erro, CPF já possui cadastro no sistema.";
                } else if (detailMessage.contains("tb_users_email_key")) {
                    errorMessage = "Erro, e-mail já possui cadastro no sistema.";
                } else {
                    errorMessage = "Erro, violação de chave única no banco de dados.";
                }
            }
        }

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                Response.Status.BAD_REQUEST.getStatusCode(),
                errorMessage
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
