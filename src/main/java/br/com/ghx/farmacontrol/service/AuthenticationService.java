package br.com.ghx.farmacontrol.service;

import br.com.ghx.farmacontrol.dto.authentication.AuthenticationResponseDTO;

public interface AuthenticationService {

    AuthenticationResponseDTO authenticate(String username, String password);

}
