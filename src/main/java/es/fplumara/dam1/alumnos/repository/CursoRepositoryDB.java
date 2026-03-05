package es.fplumara.dam1.alumnos.repository;

import es.fplumara.dam1.alumnos.model.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoRepositoryDB implements CursoRepostitory {
    String url;
    String user;
    String password;


    public CursoRepositoryDB(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }


    public void iniciarSchema() {
        String bds = """
                CREATE TABLE IF NOT EXIST curso (
                id Interger  AUTO_INCREMENT  NOT NULL ,
                estado boolean NOT NULL  ,
                nombre varchar (100) NOT NULL
                ); 
                
                
                """;
        try {
            Connection c = getConnection();
            Statement st = c.createStatement();
            st.execute(bds);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void insertCurso( Curso curso ) {


        String sql = "INSERT INTO curso ( estado, nombre ) VALUES (?,?)";

        try {
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setBoolean(1, curso.getEstado());
            ps.setString(2, curso.getNombre());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void eliminarNombre(String texto) {

        String sql = " DELETE FROM curso WHERE nombre LIKE  %?%   ";


        try {
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, texto);
            ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Curso> listarPorEstado(boolean estado) {

        String sql = "SELECT * FROM curso  WHERE estado = ? ";


        try {
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setBoolean(1, estado);
            ResultSet rs = ps.executeQuery();


            List<Curso> out = new ArrayList<>();
            while (rs.next()) {
                out.add((mapToCurso(rs)));
            }
            return out;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<Curso> listarOrdenadoPor(String campo, String orden) {


        return List.of();
    }

    public Curso mapToCurso(ResultSet rs) throws SQLException {
        return new Curso(
                rs.getInt("id"),
                rs.getBoolean("estado"),
                rs.getString("nombre")

        );
    }
}
