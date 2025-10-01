
package com.practica.app;

import com.practica.model.Categoria;
import com.practica.model.Plataforma;
import com.practica.model.Proveedor;
import com.practica.model.Videojuego;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;

import java.awt.EventQueue;
import java.awt.event.*;
import java.util.List;

public class FrmGestionVideojuegos extends JFrame {

    private JTextField txtIdJuego, txtTitulo, txtPrecio, txtStock;
    private JComboBox<Categoria> cboCategoria;
    private JComboBox<Plataforma> cboPlataforma;
    private JComboBox<Proveedor> cboProveedor;
    private JTextArea txtSalida;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmGestionVideojuegos frame = new FrmGestionVideojuegos();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public FrmGestionVideojuegos() {
        setTitle("Gestión de Videojuegos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 450);
        getContentPane().setLayout(null);

        JLabel lblIdJuego = new JLabel("Código Juego:");
        lblIdJuego.setBounds(10, 10, 100, 25);
        getContentPane().add(lblIdJuego);

        txtIdJuego = new JTextField();
        txtIdJuego.setBounds(120, 10, 150, 25);
        getContentPane().add(txtIdJuego);

        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setBounds(10, 45, 100, 25);
        getContentPane().add(lblTitulo);

        txtTitulo = new JTextField();
        txtTitulo.setBounds(120, 45, 250, 25);
        getContentPane().add(txtTitulo);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(10, 80, 100, 25);
        getContentPane().add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(120, 80, 100, 25);
        getContentPane().add(txtPrecio);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setBounds(10, 115, 100, 25);
        getContentPane().add(lblStock);

        txtStock = new JTextField();
        txtStock.setBounds(120, 115, 100, 25);
        getContentPane().add(txtStock);

        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setBounds(10, 150, 100, 25);
        getContentPane().add(lblCategoria);

        cboCategoria = new JComboBox<>();
        cboCategoria.setBounds(120, 150, 200, 25);
        getContentPane().add(cboCategoria);

        JLabel lblPlataforma = new JLabel("Plataforma:");
        lblPlataforma.setBounds(10, 185, 100, 25);
        getContentPane().add(lblPlataforma);

        cboPlataforma = new JComboBox<>();
        cboPlataforma.setBounds(120, 185, 200, 25);
        getContentPane().add(cboPlataforma);

        JLabel lblProveedor = new JLabel("Proveedor:");
        lblProveedor.setBounds(10, 220, 100, 25);
        getContentPane().add(lblProveedor);

        cboProveedor = new JComboBox<>();
        cboProveedor.setBounds(120, 220, 250, 25);
        getContentPane().add(cboProveedor);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(400, 10, 150, 25);
        getContentPane().add(btnRegistrar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(400, 45, 150, 25);
        getContentPane().add(btnBuscar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(400, 80, 150, 25);
        getContentPane().add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(400, 115, 150, 25);
        getContentPane().add(btnEliminar);

        JButton btnListar = new JButton("Listar");
        btnListar.setBounds(400, 150, 150, 25);
        getContentPane().add(btnListar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 260, 560, 140);
        getContentPane().add(scrollPane);

        txtSalida = new JTextArea();
        scrollPane.setViewportView(txtSalida);
        
        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(400, 185, 150, 25);
        getContentPane().add(btnLimpiar);

        // Llenar combos
        llenaCombos();

        // Eventos
        btnRegistrar.addActionListener(e -> registrar());
        btnBuscar.addActionListener(e -> buscar());
        btnActualizar.addActionListener(e -> actualizar());
        btnEliminar.addActionListener(e -> eliminar());
        btnListar.addActionListener(e -> listar());
        btnLimpiar.addActionListener(e -> limpiarCampos());

    }

    void llenaCombos() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql");
        EntityManager em = emf.createEntityManager();

        List<Categoria> categorias = em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
        cboCategoria.removeAllItems();
        cboCategoria.addItem(null);
        for (Categoria c : categorias) {
            cboCategoria.addItem(c);
        }

        List<Plataforma> plataformas = em.createQuery("SELECT p FROM Plataforma p", Plataforma.class).getResultList();
        cboPlataforma.removeAllItems();
        cboPlataforma.addItem(null);
        for (Plataforma p : plataformas) {
            cboPlataforma.addItem(p);
        }

        List<Proveedor> proveedores = em.createQuery("SELECT p FROM Proveedor p", Proveedor.class).getResultList();
        cboProveedor.removeAllItems();
        cboProveedor.addItem(null);
        for (Proveedor p : proveedores) {
            cboProveedor.addItem(p);
        }

        em.close();
        emf.close();
    }

    void registrar() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql");
        EntityManager em = emf.createEntityManager();

        try {
            String idJuego = txtIdJuego.getText().trim();
            String titulo = txtTitulo.getText().trim();
            double precio = Double.parseDouble(txtPrecio.getText().trim());
            int stock = Integer.parseInt(txtStock.getText().trim());

            Categoria categoria = (Categoria) cboCategoria.getSelectedItem();
            Plataforma plataforma = (Plataforma) cboPlataforma.getSelectedItem();
            Proveedor proveedor = (Proveedor) cboProveedor.getSelectedItem();

            Videojuego juego = new Videojuego();
            juego.setId_juego(idJuego);
            juego.setTitulo(titulo);
            juego.setPrecio(precio);
            juego.setStock(stock);
            juego.setId_categoria(categoria.getId_categoria());
            juego.setId_plataforma(plataforma.getId_plataforma());
            juego.setId_proveedor(proveedor.getId_proveedor());

            em.getTransaction().begin();
            em.persist(juego);
            em.getTransaction().commit();

            imprimir("Videojuego registrado correctamente.");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            imprimir("Error al registrar videojuego: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }

    void buscar() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql");
        EntityManager em = emf.createEntityManager();

        try {
            String codigo = txtIdJuego.getText().trim();
            Videojuego juego = em.find(Videojuego.class, codigo);

            if (juego != null) {
                txtTitulo.setText(juego.getTitulo());
                txtPrecio.setText(String.valueOf(juego.getPrecio()));
                txtStock.setText(String.valueOf(juego.getStock()));
                cboCategoria.setSelectedItem(juego.getCategoria());
                cboPlataforma.setSelectedItem(juego.getPlataforma());
                cboProveedor.setSelectedItem(juego.getProveedor());

                imprimir("Videojuego encontrado:\n" + juego);
            } else {
                imprimir("Videojuego no encontrado.");
            }
        } catch (Exception e) {
            imprimir("Error al buscar: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }


    void listar() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql");
        EntityManager em = emf.createEntityManager();

        try {
            List<Videojuego> juegos = em.createQuery("SELECT v FROM Videojuego v", Videojuego.class).getResultList();

            txtSalida.setText("LISTADO DE VIDEOJUEGOS:\n");
            imprimir("-----------------------------------------------------");

            for (Videojuego v : juegos) {
                imprimir("Código.....: " + v.getId_juego());
                imprimir("Título.....: " + v.getTitulo());
                imprimir("Precio.....: " + v.getPrecio());
                imprimir("Stock......: " + v.getStock());
                imprimir("Categoría..: " + v.getCategoria().getNombre());
                imprimir("Plataforma.: " + v.getPlataforma().getNombre());
                imprimir("Proveedor..: " + v.getProveedor().getNombre());
                imprimir("-----------------------------------------------------");
            }
        } catch (Exception e) {
            imprimir("Error al listar: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }


    void actualizar() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql");
        EntityManager em = emf.createEntityManager();

        try {
            String idJuego = txtIdJuego.getText().trim();
            Videojuego juego = em.find(Videojuego.class, idJuego);

            if (juego == null) {
                imprimir("No se encontró el videojuego con ID: " + idJuego);
                return;
            }

            em.getTransaction().begin();
            juego.setTitulo(txtTitulo.getText().trim());
            juego.setPrecio(Double.parseDouble(txtPrecio.getText().trim()));
            juego.setStock(Integer.parseInt(txtStock.getText().trim()));

            Categoria categoria = (Categoria) cboCategoria.getSelectedItem();
            Plataforma plataforma = (Plataforma) cboPlataforma.getSelectedItem();
            Proveedor proveedor = (Proveedor) cboProveedor.getSelectedItem();

            juego.setId_categoria(categoria.getId_categoria());
            juego.setId_plataforma(plataforma.getId_plataforma());
            juego.setId_proveedor(proveedor.getId_proveedor());

            em.getTransaction().commit();

            imprimir("Videojuego actualizado correctamente.");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            imprimir("Error al actualizar: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }

    void eliminar() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql");
        EntityManager em = emf.createEntityManager();

        try {
            String idJuego = txtIdJuego.getText().trim();
            Videojuego juego = em.find(Videojuego.class, idJuego);

            if (juego == null) {
                imprimir("No se encontró el videojuego con ID: " + idJuego);
                return;
            }

            em.getTransaction().begin();
            em.remove(juego);
            em.getTransaction().commit();

            imprimir("Videojuego eliminado correctamente.");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            imprimir("Error al eliminar: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
    
    void imprimir(String mensaje) {
        txtSalida.append(mensaje + "\n");
    }

    void limpiarCampos() {
        txtIdJuego.setText("");
        txtTitulo.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        cboCategoria.setSelectedIndex(0);
        cboPlataforma.setSelectedIndex(0);
        cboProveedor.setSelectedIndex(0);
        txtSalida.setText("");
    }
    


}