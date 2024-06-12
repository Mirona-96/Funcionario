import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Funcionario_swing {
    private JButton insertButton;
    private JTextField codigoTxtField;
    private JTextField nomeTxtField;
    private JTextField diasTrabalhadosTxtField;
    private JButton listarButton;
    private JButton apagarButton;
    private JButton cancelarButton;
    private JTable tblFuncionarioData;
    private JPanel Main;
    private JTextField salarioDiarioTxtField;
    private JPanel inputPanel;
    private JPanel btnPanel;
    private JButton editarButton;
    Random random = new Random();
    static PreparedStatement stmt;
    static Connection conn;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Funcionario_swing");
        frame.setContentPane(new Funcionario_swing().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Funcionario_swing() {
       // inicializaTable();
        configureListeners();
       // table_load();
      //  listar(); // List all employees on startup
        tblFuncionarioData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DefaultTableModel listarNaTabela = (DefaultTableModel) tblFuncionarioData.getModel();
                int selectedRowIndex = tblFuncionarioData.getSelectedRow();

                codigoTxtField.setText(listarNaTabela.getValueAt(selectedRowIndex, 0).toString());
                nomeTxtField.setText(listarNaTabela.getValueAt(selectedRowIndex, 1).toString());
                diasTrabalhadosTxtField.setText(listarNaTabela.getValueAt(selectedRowIndex, 2).toString());
                salarioDiarioTxtField.setText(listarNaTabela.getValueAt(selectedRowIndex, 3).toString());
            }
        });
    }

    private void limparCampos() {
        codigoTxtField.setText("");
        nomeTxtField.setText("");
        diasTrabalhadosTxtField.setText("");
        salarioDiarioTxtField.setText("");
    }

    private void limparTabela() {

        DefaultTableModel listarNaTabela = (DefaultTableModel) tblFuncionarioData.getModel();
        listarNaTabela.setRowCount(0);
        //listarNaTabela.removeRow(0);
    }


    JScrollPane scrollPane = new JScrollPane();
    /*private void inicializaTable(){
        tblFuncionarioData.setModel(new DefaultTableModel(new Object[][]{}, new String[]{
                "CODIGO", "NOME", "DIAS DE TRABALHO", "SALARIO DIARIO", "SALARIO MENSAL"
        }));
    }*/

void table_load(){
    try {
        conn = Conexao.conectar();
        stmt = null;
        stmt = conn.prepareStatement("SELECT * FROM funcionarios;");
        ResultSet rs = stmt.executeQuery();
        tblFuncionarioData.setModel(DbUtils.resultSetToTableModel(rs));

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    private void listar() {
    table_load();
       /* DefaultTableModel listarNaTabela = (DefaultTableModel) tblFuncionarioData.getModel();
        listarNaTabela.setRowCount(0); // Clear the table before listing
        try {
            ArrayList<Funcionario> listaFuncionario = ControllerFuncionario.listaFuncionario();
            for (Funcionario funcionario : listaFuncionario) {
                int codigo = funcionario.getCodigo();
                String nome = funcionario.getNome();
                int diasTrabalhados = funcionario.getDiasTrabalho();
                double salarioDiario = funcionario.getSalarioDiario();
                double salarioMensal = funcionario.calcSalario();
                listarNaTabela.addRow(new Object[]{codigo, nome, diasTrabalhados, salarioDiario, salarioMensal});
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }*/
    }

    private void configureListeners() {
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = 1000 + random.nextInt(9000);
                String nome = nomeTxtField.getText();
                int diasTrabalhados = Integer.parseInt(diasTrabalhadosTxtField.getText());
                double salarioDiario = Double.parseDouble(salarioDiarioTxtField.getText());
                double salarioMensal = salarioDiario * diasTrabalhados;

                try {
                    ControllerFuncionario.adicionarFuncionario(codigo, nome, diasTrabalhados, salarioDiario, salarioMensal);
                    limparCampos();
                    limparTabela();
                    table_load();
                   // listar();
                    JOptionPane.showMessageDialog(null, "ADICIONADO COM SUCESSO");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparTabela();
                listar();
            }
        });

        apagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(codigoTxtField.getText());
                JOptionPane.showMessageDialog(null,"ELIMINADO COM SUCESSO");
                limparTabela();
                table_load();
                try {
                    ControllerFuncionario.remover(codigo);
                } catch (SQLException ex) {
                   ex.printStackTrace();
                }
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(codigoTxtField.getText());
                String nome = nomeTxtField.getText();
                int diasTrabalhados = Integer.parseInt(diasTrabalhadosTxtField.getText());
                double salarioDiario = Double.parseDouble(salarioDiarioTxtField.getText());
                double salarioMensal = salarioDiario * diasTrabalhados;

                try {
                    ControllerFuncionario.actualizarFuncionario(codigo, nome,diasTrabalhados,salarioDiario, salarioMensal);
                    limparCampos();
                    limparTabela();
                    table_load();
                    // listar();
                    JOptionPane.showMessageDialog(null, "EDITADO COM SUCESSO");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
