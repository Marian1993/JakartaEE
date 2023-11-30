package org.eclipse.jakarta.hello.service;


import org.eclipse.jakarta.hello.dao.FotoDaoI;
import org.eclipse.jakarta.hello.model.Foto;

import java.util.List;

public class FotoDataBaseService implements FotoServiceI {

    private final FotoDaoI fotoDao;


    public FotoDataBaseService(FotoDaoI fotoDao){
        this.fotoDao = fotoDao;
    }

    @Override
    public List<Foto> findAll(){
        //FotoDao fotoDao = new FotoDao();
        return  fotoDao.findAll();
    }

    @Override
    public void insert(Foto foto){
        fotoDao.save(foto);
    }
}
