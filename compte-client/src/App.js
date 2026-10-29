import React, { useState, useEffect, useCallback } from 'react';
import axios from 'axios';
import API_BASE_URL from './config';
import CompteList from './components/CompteList';
import CompteForm from './components/CompteForm';

function App() {
  // L'état de la liste des comptes est remonté ici, partagé entre les deux composants
  const [comptes, setComptes] = useState([]);

  // Fonction de récupération des comptes depuis l'API REST
  const fetchComptes = useCallback(() => {
    axios.get(`${API_BASE_URL}/comptes`)
      .then(response => setComptes(response.data))
      .catch(error => console.error(error));
  }, []);

  // Chargement initial de la liste
  useEffect(() => {
    fetchComptes();
  }, [fetchComptes]);

  return (
    <div>
      {/* onAdded déclenche le rafraîchissement de la liste après un ajout */}
      <CompteForm onAdded={fetchComptes} />
      <CompteList comptes={comptes} />
    </div>
  );
}

export default App;
