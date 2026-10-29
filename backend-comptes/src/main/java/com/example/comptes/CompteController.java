package com.example.comptes;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/comptes")
@CrossOrigin(origins = "http://localhost:3000")
public class CompteController {

    // Stockage en mémoire (pas de base de données pour ce backend minimal)
    private final List<Compte> comptes = new CopyOnWriteArrayList<>();
    private final AtomicLong compteurId = new AtomicLong(0);

    @GetMapping
    public List<Compte> lister() {
        return new ArrayList<>(comptes);
    }

    @PostMapping
    public Compte ajouter(@RequestBody Compte compte) {
        compte.setId(compteurId.incrementAndGet());
        comptes.add(compte);
        return compte;
    }
}
