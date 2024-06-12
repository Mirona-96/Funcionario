import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControllerFuncionario {

    static PreparedStatement stmt;
    static Connection conn;
    public static void adicionarFuncionario(int codigo, String nome, int diasTrabalhados, double salarioDiario, double salarioMensal) throws SQLException {
        conn = Conexao.conectar();
        stmt = null;
        stmt = conn.prepareStatement("INSERT INTO funcionarios (Codigo, Nome, DiasTrabalhados, SalarioDiario, SalarioMensal) VALUES (?, ?, ?, ?, ?)");
        stmt.setInt(1, codigo);
        stmt.setString(2, nome);
        stmt.setInt(3, diasTrabalhados);
        stmt.setDouble(4, salarioDiario);
        stmt.setDouble(5, salarioMensal);
        stmt.executeUpdate();
        conn.close();
    }

    public  static ArrayList<Funcionario> listaFuncionario() throws SQLException {
        ArrayList<Funcionario>funcionarios = new ArrayList<Funcionario>();

        conn = Conexao.conectar();
        stmt = null;
        stmt = conn.prepareStatement("SELECT * FROM funcionarios");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            int codigo = rs.getInt(1);
            String nome = rs.getString(2);
            int diasTrabalhados = rs.getInt(3);
            double salarioDiaro = rs.getDouble(4);
            double salarioMensal = rs.getDouble(5);
            funcionarios.add(new Funcionario(codigo,nome,diasTrabalhados,salarioDiaro,salarioMensal));
        }
        conn.close();
        return funcionarios;
    }

    public static void actualizarFuncionario(int codigo, String nome, int diasTrabalhados, double salarioDiario, double salarioMensal) throws SQLException {
        conn = Conexao.conectar();
        stmt = null;
        stmt = conn.prepareStatement("UPDATE funcionarios set Nome=?, DiasTrabalhados=?, SalarioDiario = ?, SalarioMensal = ? WHERE Codigo=?");
        stmt.setString(1,nome);
        stmt.setInt(2,diasTrabalhados);
        stmt.setDouble(3, salarioDiario);
        stmt.setDouble(4, salarioMensal);
        stmt.setInt(5, codigo);
        stmt.executeUpdate();
        conn.close();
    }

    public static void remover(int codigo) throws SQLException {
        conn = Conexao.conectar();
        stmt = null;
        stmt = conn.prepareStatement("delete from funcionarios where Codigo = ?");
        stmt.setInt(1,codigo);
        stmt.executeUpdate();
        conn.close();
    }

}
