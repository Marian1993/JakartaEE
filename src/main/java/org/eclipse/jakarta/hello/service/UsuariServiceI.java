package org.eclipse.jakarta.hello.service;

import org.eclipse.jakarta.hello.model.Usuari;

public interface UsuariServiceI extends CrudServiceI<Usuari>{
    Usuari findUsuariByUsernameAndPassword(String user,String password);
}
