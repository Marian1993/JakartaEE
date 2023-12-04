package org.eclipse.jakarta.hello.dao;

import org.eclipse.jakarta.hello.config.MysqlConnection;
import org.eclipse.jakarta.hello.model.Usuari;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuariDao implements UsuariDaoI{

    public List<Usuari> findAll() {
        try{
            MysqlConnection connection = MysqlConnection.getInstance();

            String sql = "select * from usuari";

            PreparedStatement ps = connection.getConnexio().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            //1-Crear objecte a retornar
            List<Usuari> resultat = new ArrayList<>();
            //2-Recorrem rs i emplenam la llista
            while (rs.next()){
                Usuari usuari = new Usuari();
                usuari.setNom(rs.getString("nom"));
                usuari.setContrasenya(rs.getString("password"));

                resultat.add(usuari);
            }
            return resultat;

        }catch (Exception e){
            System.out.println("Error findAllFotos: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Usuari findUsuariByUsernameAndPassword(String user, String password) {
        try {
            MysqlConnection connection = MysqlConnection.getInstance();

            String sql = "select * from usuari where nom=? and password=?";
            PreparedStatement ps = connection.getConnexio().prepareStatement(sql);
            ps.setString(1,user);
            ps.setString(2,password);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()){
                Usuari usuari = new Usuari();
                usuari.setNom(resultSet.getString("nom"));
                usuari.setContrasenya(resultSet.getString("password"));

                return usuari;
            }
            return null;
        }catch (Exception e) {
            System.out.println("Error findAllFotos: " + e.getMessage());
            return null;
        }
    }

    public void save(Usuari usuari) {
        try{

            MysqlConnection connection = MysqlConnection.getInstance();
            String sql = "insert into usuari(nom,password) values (?,?)";
            PreparedStatement preparedStatement = connection.getConnexio().prepareStatement(sql);
            preparedStatement.setString(1, usuari.getNom());
            preparedStatement.setString(2, usuari.getContrasenya());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
