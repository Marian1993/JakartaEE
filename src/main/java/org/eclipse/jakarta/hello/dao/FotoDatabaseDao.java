package org.eclipse.jakarta.hello.dao;

import org.eclipse.jakarta.hello.config.MysqlConnection;
import org.eclipse.jakarta.hello.model.Foto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FotoDatabaseDao implements FotoDaoI{
    @Override
    public List<Foto> findAll(){

        try{
            MysqlConnection connection = MysqlConnection.getInstance();

            String sql = "select * from foto";

            PreparedStatement ps = connection.getConnexio().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            //1-Crear objecte a retornar
            List<Foto> resultat = new ArrayList<>();
            //2-Recorrem rs i emplenam la llista
            while (rs.next()){
                Foto foto = new Foto();
                foto.setPrivada(false);
                foto.setUrl(rs.getString("url"));
                foto.setNom(rs.getString("nom"));

                resultat.add(foto);
            }
            return resultat;

        }catch (Exception e){
            System.out.println("Error findAllFotos: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void save(Foto foto) {
        try{

            MysqlConnection connection = MysqlConnection.getInstance();
            String sql = "insert into foto(nom,url) values (?,?)";
            PreparedStatement preparedStatement = connection.getConnexio().prepareStatement(sql);
            preparedStatement.setString(1, foto.getNom());
            preparedStatement.setString(2, foto.getUrl());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
