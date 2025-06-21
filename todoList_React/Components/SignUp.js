import React, { useState } from "react";
import { View, Text, TouchableOpacity, TextInput, StyleSheet,ImageBackground } from "react-native";
import { Link } from "@react-navigation/native"; 
import { SessionContext } from "../Context/Context";
import { signUp } from "../js/sign"; 


// Composant pour s'inscrire
export default function SignUp() {

  const [username, setUsername] = useState(""); //etat pour gerer le nom d'utilisateur
  const [password, setPassword] = useState(""); 
  const [errorMessage, setErrorMessage] = useState(""); 

  // Fonction de gestion de l'inscription
  function getSignUp(setSession) {
    signUp(username, password) 
      .then((token) => {
        setSession({ token, username });  
        setErrorMessage(""); 
      })
      .catch((err) => setErrorMessage(err.message)); 
  }
  return (
    <SessionContext.Consumer>
      {([session, setSession]) => (
        <ImageBackground
          source={require("../assets/bgSignn.png")} 
          style={styles.background} 
        >
          {/* Overlay semi-transparent pour améliorer la lisibilité du contenu */}
          <View style={styles.overlay}>
            {/* Card principale contenant le formulaire */}
            <View style={styles.containerCard}>
              
              {/* Titre principal de la page d'inscription */}
              <Text style={styles.entete}>Inscription</Text>

              {/* Label pour le champ de nom d'utilisateur */}
              <Text style={styles.champLabel}>Login :</Text>
              {/* Champ de saisie pour le nom d'utilisateur */}
              <TextInput
                style={styles.input} 
                value={username}
                onChangeText={setUsername}
                placeholder="Veuillez saisir votre nom d'utilisateur" 
                placeholderTextColor="#888"
              />

              {/* Label pour le champ de mot de passe */}
              <Text style={styles.champLabel}>Mot de passe :</Text>
              {/* Champ de saisie pour le mot de passe */}
              <TextInput
                style={styles.input} 
                value={password} 
                secureTextEntry 
                onChangeText={setPassword} 
                placeholder="Veuillez saisir votre mot de passe" 
                placeholderTextColor="#888" 
              />

              {/* Affichage du message d'erreur, si présent */}
              {errorMessage ? <Text style={styles.error}>{errorMessage}</Text> : null}

              {/* Bouton pour soumettre le formulaire d'inscription */}
              <TouchableOpacity
                style={styles.submitButton} 
                onPress={() => getSignUp(setSession)} 
              >
                <Text style={styles.textButton}>Inscrivez-vous !</Text> 
              </TouchableOpacity>
              <Text style={styles.textSignin}>
                Vous avez déjà un compte ? {" "}
                {/* Lien de navigation vers la page de connexion */}
                <Link style={styles.link} to={{ screen: "SignIn" }}>
                  Connectez-vous ici!
                </Link>
              </Text>
            </View>
          </View>
        </ImageBackground>
      )}
    </SessionContext.Consumer>
  );
}
const styles = StyleSheet.create({
  // Style pour l'image de fond
  background: {
    flex: 1,
    justifyContent: "center", 
  },
  // Overlay semi-transparent pour rendre l'arrière-plan visible tout en améliorant la lisibilité
  overlay: {
    flex: 1,
    //backgroundColor: "rgba(0, 0, 0, 0.4)", 
    justifyContent: "center", 
    alignItems: "center", 
  },
  // Carte contenant le formulaire
  containerCard: {
    height: "45%",
    width: "50%", 
    padding: 21, 
    backgroundColor: "rgba(255, 255, 255, 0.7)", 
    alignItems: "center", 
  },
  
  // Titre principal de l'inscription
  entete: {
    fontWeight: "bolder", 
    fontSize: 24, 
    color: "#black",
    marginBottom: 22, 
    textTransform: "uppercase",
  },
  // Label des champs de saisie
  champLabel: {
    color: "black",
    fontSize: 15, 
    textTransform: "uppercase",
    alignSelf: "flex-start", 
    marginBottom: 5, 
    fontWeight: "bolder", 
  },
  // Champ de saisie des informations (nom d'utilisateur et mot de passe)
  input: {
    width: "100%", 
    padding: 16, 
    fontSize: 15, 
    borderRadius: 10, 
    marginBottom: 20, 
    backgroundColor: "#fafafa", 
  },
  // Bouton de soumission
  submitButton: {
    textTransform: "uppercase",
    width: "70%", 
    fontWeight: "bolder", 
    backgroundColor: "#76CDCD", 
    paddingVertical: 15, 
    borderRadius: 10, 
    alignItems: "center", 
    marginTop: 10, 
  },
  // Texte du bouton de soumission
  textButton: {
    color: "black", 
    fontSize: 15, 
    fontWeight: "bolder", 
  },
  
  // Style du lien pour la page de connexion
  link: {
    color: "#5784BA", 
    fontWeight: "bolder", 
    textDecorationLine: "underline",
  },
  textSignin: {
    marginTop: 20, 
    fontSize: 14, 
    color: "#666",
  },
});
