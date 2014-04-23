package bds.devweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bds.devweb.model.EquipeSport;

public class EquipeSportDao {
	
	public List<EquipeSport> listerEquipeSportsbySport (String identifiant){
		List<EquipeSport> listeEquipeSport = new ArrayList<EquipeSport>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT sport.*, equipe_sport.* FROM equipe_sport INNER JOIN sport ON sport.id_sport = equipe_sport.id_sport WHERE id_sport = ?");
			stmt.setString(1,  identifiant);
			ResultSet results = stmt.executeQuery();
			while(results.next()){
				EquipeSport equipesport = new EquipeSport(
						results.getString("sport.id_sport"),
						results.getString("sport.nom_sport"),
						results.getString("id_equipeSport"),
						results.getString("nom_equipeSport"));
					listeEquipeSport.add(equipesport);
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return listeEquipeSport;
	}
	
	public List<EquipeSport> listerEquipeSport (){
		List<EquipeSport> listeEquipeSport = new ArrayList<EquipeSport>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("SELECT sport.*, equipe_sport.* FROM equipe_sport INNER JOIN sport ON sport.id_sport = equipe_sport.id_sport");
			ResultSet results = stmt.executeQuery();
			while(results.next()){
				EquipeSport equipesport = new EquipeSport(
						results.getString("sport.id_sport"),
						results.getString("sport.nom_sport"),
						results.getString("id_equipeSport"),
						results.getString("nom_equipeSport"));
					listeEquipeSport.add(equipesport);
			}
			//Fermer la connexion
			results.close();
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return listeEquipeSport;
	}
	
	public void deleteEquipeSport (String id_equipeSport){
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM equipe_sport WHERE id_equipeSport = ?");
			PreparedStatement stmts = connection.prepareStatement("DELETE FROM vp WHERE id_equipeSport = ?");
			stmt.setString(1,  id_equipeSport);
			stmts.setString(1,  id_equipeSport);
			stmt.executeUpdate();
			stmts.executeUpdate();
			//Fermer la connexion
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void ajouterEquipeSport (String id_equipeSport, String nom_equipeSport, String id_categorie, String id_sport){
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			//Utiliser la connexion
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO equipe_sport (id_equipeSport, nom_equipeSport, id_categorie, id_sport) VALUES (?, ?, ?, ?)");
			stmt.setString(1,  id_equipeSport);
			stmt.setString(2,  nom_equipeSport);
			stmt.setString(3,  id_categorie);
			stmt.setString(4,  id_sport);
			stmt.executeUpdate();
			//Fermer la connexion
			stmt.close();
			connection.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
	

}
