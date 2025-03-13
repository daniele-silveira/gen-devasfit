package com.generation.devasfit.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.devasfit.model.Treinos;
import com.generation.devasfit.model.Usuario;
import com.generation.devasfit.repository.TreinosRepository;
import com.generation.devasfit.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private TreinosRepository treinoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	//bucar por id
	public Optional<Treinos> buscarTreinoPorId(Long id) {
        return treinoRepository.findById(id);
    }
	
	// Calcular IMC do usuário
    public float calcularIMC(Usuario usuario) {
        Optional<Usuario> buscaUsuario = usuarioRepository.findByUsuario(usuario);

        if(usuarioRepository.findByUsuario(usuario.get()).isPresent()) {
           
        	float peso = usuario.get()
            float altura = usuario.getLong(null);

            if (altura > 0) {
                return peso / (altura * altura);
            } else {
                throw new IllegalArgumentException("Altura deve ser maior que zero.");
            }
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

}