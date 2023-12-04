package org.eclipse.jakarta.hello.service;

import org.eclipse.jakarta.hello.dao.UsuariDao;
import org.eclipse.jakarta.hello.dao.UsuariDaoI;
import org.eclipse.jakarta.hello.model.Usuari;

public class UsuariService implements UsuariServiceI {


    private  final UsuariDaoI usuariDao;
    public UsuariService(UsuariDaoI session){

        this.usuariDao = session;
    }


    @Override
    public void insert(Usuari usuari){
        usuariDao.save(usuari);
    }

    @Override
    public Usuari findUsuariByUsernameAndPassword(String user, String password) {
        return this.usuariDao.findUsuariByUsernameAndPassword(user,password);
    }
}
