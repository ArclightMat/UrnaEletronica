import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class SQLConnect {

    private String url = "jdbc:mysql://localhost:3306/eleiçao";
    private String username = "root";
    private String password = "root";
    private Connection connect = null;
    private PreparedStatement verificaSt = null;
    private ResultSet verificaRs = null;
    private PreparedStatement atualizaSt = null;
    private Statement apurarGovSt = null;
    private ResultSet apurarGovRs = null;
    private Statement apurarPrezSt = null;
    private ResultSet apurarPrezRs = null;
    private String nome, partido, cargo, vice;
    private int numPart;

    public SQLConnect() {
        // Debugging
        // System.out.println("Conectando ao banco de dados...");
        try {
            connect = DriverManager.getConnection(url, username, password);
            // Debugging
            // System.out.println("Conectado com sucesso!");
        } catch (SQLException e) {
            throw new IllegalStateException("Falha ao conectar.", e);
        }
    }

    public void reinicializa() {
        nome = "";
        partido = "";
        cargo = "";
        vice = "";
        numPart = -1;
    }

    public void setNumPart(int numPart) {
        this.numPart = numPart;
    }
    public void setCargo (String cargo) {
        this.cargo = cargo.toLowerCase();
    }
    public int getNumPart() {
        return numPart;
    }
    public String getNome() {
        return nome;
    }
    public String getPartido() {
        return partido;
    }
    public String getCargo() {
        return cargo;
    }
    public String getVice() {
        return vice;
    }

    public void verificar() {
        try {
            if (cargo.equals("governador")) {
                if (numPart != 13 && numPart != 16 && numPart != 2) {
                    numPart = 1;
                }
                verificaSt = connect.prepareStatement("SELECT nome, partido, vice_nome from governador where num_partido = ?;");
            }
            else {
                if (numPart != 30 && numPart != 15 && numPart != 2) {
                    numPart = 1;
                }
                verificaSt = connect.prepareStatement("SELECT nome, partido, vice_nome from presidente where num_partido = ?;");
            }
            verificaSt.setInt(1, numPart);
            verificaRs = verificaSt.executeQuery();

            while(verificaRs.next()) {
                nome = verificaRs.getString("nome");
                partido = verificaRs.getString("partido");
                vice = verificaRs.getString("vice_nome");
            }
            // Para debugging
            // System.out.println(numPart + nome + cargo + partido + vice);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar() {
        try {
            if (cargo.equals("governador")) {
                atualizaSt = connect.prepareStatement("UPDATE governador SET votos = votos + 1 WHERE num_partido = ?;");
            }
            else {
                atualizaSt = connect.prepareStatement("UPDATE presidente SET votos = votos + 1 WHERE num_partido = ?;");
            }
            atualizaSt.setInt(1, numPart);
            atualizaSt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String apurarGov() {
        int i = 0;
        String[] resultados = new String[4];
        String resultadoFinal = "Resultados para Governador: \n\n";
        try {
            apurarGovSt = connect.createStatement();
            apurarPrezSt = connect.createStatement();
            apurarGovRs = apurarGovSt.executeQuery("select nome, votos from governador order by votos DESC;");
            apurarPrezRs = apurarPrezSt.executeQuery("select nome, votos from presidente order by votos DESC;");;
            while (apurarGovRs.next()) {
                resultados[i] = apurarGovRs.getString(1) + ": " + apurarGovRs.getString(2);
                resultadoFinal += "\n" + resultados[i];
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return resultadoFinal;
    }
    public String apurarPrez() {
        int i = 0;
        String[] resultados = new String[4];
        String resultadoFinal = "Resultados para Presidente: \n\n";
        try {
            apurarPrezSt = connect.createStatement();
            apurarPrezRs = apurarPrezSt.executeQuery("select nome, votos from presidente order by votos DESC;");;
            while (apurarPrezRs.next()) {
                resultados[i] = apurarPrezRs.getString(1) + ": " + apurarPrezRs.getString(2);
                resultadoFinal += "\n" + resultados[i];
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return resultadoFinal;
    }
}
