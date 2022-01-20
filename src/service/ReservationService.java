package service;

import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

import beans.Reservation;
import beans.Salle;
import connexion.Connexion;
import dao.IDao;

public class ReservationService implements IDao<Reservation> {

    @Override
    public boolean create(Reservation o) {
        String sql = "insert into reservation values (null, ?, ?, ?,?)";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setString(1, o.getReference());
            ps.setDate(2, new Date(o.getDateReservation().getTime()));
            ps.setString(3, o.getnomClient());
            ps.setInt(4, o.getIdSalle());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("create : erreur sql : " + e.getMessage());

        }
        return false;
    }

    @Override
    public boolean delete(Reservation o) {
        String sql = "delete from reservation where id  = ?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setInt(1, o.getId());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("delete : erreur sql : " + e.getMessage());

        }
        return false;
    }

    @Override
    public boolean update(Reservation o) {

        String sql = "update reservation set reference  = ? ,dateReservation = ? , nomClient = ?, idSalle=? where id  = ?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setString(1, o.getReference());
            ps.setDate(2, new Date(o.getDateReservation().getTime()));
            ps.setString(3, o.getnomClient());
            ps.setInt(4, o.getIdSalle());
            ps.setInt(5, o.getId());
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("update : erreur sql : " + e.getMessage());

        }
        return false;
    }

    @Override
    public Reservation findById(int id) {
        Reservation m = null;
        String sql = "select * from reservation where id  = ?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Reservation(rs.getInt("id"), rs.getString("reference"), rs.getDate("dateReservation"),
                        rs.getString("nomClient"),rs.getInt("idSalle"));
            }

        } catch (SQLException e) {
            System.out.println("findById " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Reservation> findAll() {
        List<Reservation> reservations = new ArrayList<Reservation>();

        String sql = "select * from reservation";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	reservations.add(new Reservation(rs.getInt("id"), rs.getString("reference"), rs.getDate("dateReservation"),
                        rs.getString("nomClient"),rs.getInt("idSalle")));
            }

        } catch (SQLException e) {
            System.out.println("findAll " + e.getMessage());
        }
        return reservations;
    }
    
    
    public List<Reservation> findAll2() {
    	SalleService ms = new SalleService();
        List<Reservation> reservations = new ArrayList<Reservation>();

        String sql = "select * from reservation";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	reservations.add(new Reservation(rs.getInt("id"), rs.getString("reference"), rs.getDate("dateReservation"),
                        rs.getString("nomClient"),ms.findById(rs.getInt("idSalle"))));
            }

        } catch (SQLException e) {
            System.out.println("findAll " + e.getMessage());
        }
        return reservations;
    }
    
    
    public List<Salle> findAllSalle() {
        List<Salle> salles = new ArrayList<Salle>();

        String sql = "select * from salle";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                salles.add(new Salle(rs.getInt("id"), rs.getString("libelle")));
            }

        } catch (SQLException e) {
            System.out.println("findAll " + e.getMessage());
        }
        return salles;
    }
    
    public List<Reservation> findReservationByReference(String ref) {
        List<Reservation> reservations = new ArrayList<Reservation>();

        String sql = "select * from reservation where reference =  ?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);;
            ps.setString(1, ref);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	reservations.add(new Reservation(rs.getInt("id"), rs.getString("reference"), rs.getDate("dateReservation"),
                        rs.getString("nomClient"),rs.getInt("idSalle")));
            }

        } catch (SQLException e) {
            System.out.println("findAll " + e.getMessage());
        }
        return reservations;
    }
    
    public List<String> findReference() {
        List<String> references = new ArrayList<String>();
        String sql = "select distinct(reference) as ref from reservation";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                references.add(rs.getString("ref"));
            }
        } catch (SQLException e) {
            System.out.println("findReference " + e.getMessage());
        }
        return references;
    }

    public List<Reservation> findReservationBySalle(int idM) {
        List<Reservation> reservations = new ArrayList<Reservation>();
        SalleService ms = new SalleService();
        String sql = "select * from reservation where idSalle =  ?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);;
            ps.setInt(1, idM);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	reservations.add(new Reservation(rs.getInt("id"), rs.getString("reference"), rs.getDate("dateReservation"),
                        rs.getString("nomClient"),ms.findById(rs.getInt("idSalle"))));
            }

        } catch (SQLException e) {
            System.out.println("findAll " + e.getMessage());
        }
        return reservations;
    }
    
    public List<Reservation> findReservationBetweenDate(String d1, String d2) {
    	SalleService ms = new SalleService();
        List<Reservation> reservations = new ArrayList<Reservation>();

        String sql = "select * from reservation where dateReservation between ? and ?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);;
            ps.setString(1, d1);
            ps.setString(2, d2);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	reservations.add(new Reservation(rs.getInt("id"), rs.getString("reference"), rs.getDate("dateReservation"),
                        rs.getString("nomClient"),ms.findById(rs.getInt("idSalle"))));
            }

        } catch (SQLException e) {
            System.out.println("findAll " + e.getMessage());
        }
        return reservations;
    }
    
    
    public List<Reservation> findAll3() {
    	SalleService ms = new SalleService();
        List<Reservation> reservations = new ArrayList<Reservation>();

        String sql = "select m.id, m.reference, m.idSalle ,count(*) as count from reservation m inner join salle ma on m.idSalle = ma.id group by m.idSalle";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	reservations.add(new Reservation(rs.getInt("id"), rs.getString("reference"),
                        ms.findById(rs.getInt("idSalle")),rs.getInt("count")));
            }

        } catch (SQLException e) {
            System.out.println("findAll " + e.getMessage());
        }
        return reservations;
    }
    
    public int countReservation() {
    	SalleService ms = new SalleService();
        int count = 0 ;

        String sql = "select count(*) as count from reservation ";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }

        } catch (SQLException e) {
            System.out.println("findAll " + e.getMessage());
        }
        return count;
    }



}
