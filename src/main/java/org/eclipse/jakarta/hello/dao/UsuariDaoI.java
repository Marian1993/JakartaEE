package org.eclipse.jakarta.hello.dao;

import org.eclipse.jakarta.hello.model.Foto;
import org.eclipse.jakarta.hello.model.Usuari;

import java.util.List;

public interface UsuariDaoI {

    List<Usuari> findAll();
    Usuari findUsuariByUsernameAndPassword(String user,String password);
    void save(Usuari usuari);
}
