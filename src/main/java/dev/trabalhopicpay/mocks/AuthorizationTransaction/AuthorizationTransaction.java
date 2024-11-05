package dev.trabalhopicpay.mocks.AuthorizationTransaction;

import dev.trabalhopicpay.dtos.responses.ResponseAuthorizationTransaction;
import jakarta.ws.rs.Consumes;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

// Serviço que autoriza transação (true ou false)
@Path("/api/v2/authorize")
// @Path("https://util.devi.tools/api/v2/authorize")
@RegisterRestClient(configKey = "mock-api")
public interface AuthorizationTransaction {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ResponseAuthorizationTransaction authorize();
}
