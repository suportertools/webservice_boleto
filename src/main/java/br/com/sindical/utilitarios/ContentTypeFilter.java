/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sindical.utilitarios;

/**
 *
 * @author Claudemir Rtools
 */
import java.io.IOException;
import java.util.List;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;


@PreMatching
public class ContentTypeFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext)
            throws IOException {
        MultivaluedMap<String, String> headers = requestContext.getHeaders();
        List<String> contentTypes = headers.remove(HttpHeaders.CONTENT_TYPE);
        if (contentTypes != null && !contentTypes.isEmpty()) {
            String contentType = contentTypes.get(0);
            String sanitizedContentType = contentType.replaceFirst(";.*", "");
            headers.add(HttpHeaders.CONTENT_TYPE, sanitizedContentType);
        }
        System.err.println("Entrou aqui!!!!!!!!!!!!!!");
    }
}
