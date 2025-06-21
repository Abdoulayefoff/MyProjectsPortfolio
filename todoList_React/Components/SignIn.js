import React, { useState } from "react"; 
import { View, Text, TouchableOpacity, TextInput, StyleSheet,ImageBackground } from "react-native";
import { Link } from "@react-navigation/native";
import { SessionContext } from "../Context/Context";
import { signIn } from "../js/sign";


//Composant signIn pour se connecter
export default function SignIn() {
  
  const [username, setUsername] = useState(""); //un etat pour gerer le nom d'utilisateur
  const [password, setPassword] = useState(""); 
  const [errorMessage, setErrorMessage] = useState("");

  // Fonction de gestion de la connexion
  function getSignIn(setSession) {
    signIn(username, password)
      .then((token) => {
        setSession({ token, username });  
        setErrorMessage("");
      })
      .catch((error) => setErrorMessage(error.message));
}


  // Rendu de l'interface utilisateur avec le contexte de session
  return (
    <SessionContext.Consumer>
      {([session, setSession]) => (
        // Image de fond
        <ImageBackground source={require("../assets/bgSignn.png")} style={styles.background}>
          {/* Overlay pour améliorer la lisibilité sur l'image de fond */}
          <View style={styles.overlay}>
            {/* Carte contenant le formulaire */}
            <View style={styles.containerCard}>
              
              {/* En-tête de connexion */}
              <Text style={styles.entete}>Connexion </Text>

              {/* Champ pour le nom d'utilisateur */}
              <Text style={styles.champLabel}>Login :</Text>
              <TextInput
                style={styles.input}
                value={username}
                onChangeText={setUsername} // Mise à jour du nom d'utilisateur dans l'état
                placeholder="Veuillez saisir votre nom d'utilisateur svp !"
                placeholderTextColor="##4AA3A2" 
              />

              {/* Champ pour le mot de passe */}
              <Text style={styles.champLabel}>Password :</Text>
              <TextInput
                style={styles.input}
                value={password}
                secureTextEntry 
                onChangeText={setPassword} 
                placeholder="Veuillez saisir votre mot de passe svp !"
                placeholderTextColor="##4AA3A2" 
              />

              {/* Message d'erreur */}
              {errorMessage ? <Text style={styles.error}>{errorMessage}</Text> : null}

              {/* Bouton pour soumettre le formulaire de connexion */}
              <TouchableOpacity style={styles.submitButton} onPress={() => getSignIn(setSession)}>
                <Text style={styles.textButton}>Soumettre </Text>
              </TouchableOpacity>

              {/* Lien vers la page de création de compte */}
              <Text style={styles.textSignup}>
                  Besoin d'un compte ? {" "}
                <Link style={styles.link} to={{ screen: "SignUp" }}>
                  Inscrivez-vous ici !
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
  // Style pour la carte contenant le formulaire
  containerCard: {
    alignItems: "center", 
    padding: 21, 
    width: "50%",
    height: "45%",
    backgroundColor: "rgba(255, 255, 255, 0.7)", // Fond blanc semi-transparent
    borderRadius: 20, 
  },
  // Overlay légèrement transparent pour mieux voir l'image de fond
  overlay: {
    flex: 1, 
    //backgroundColor: "rgba(0, 0, 0, 0.3)", 
    alignItems: "center",
    justifyContent: "center", 
  },
  
  
  // Style pour l'en-tête
  entete: {
    fontWeight: "bolder", 
    textTransform: "uppercase", 
    fontSize: 24, 
    color: "black", 
    marginBottom: 22, 
  },
  // Style pour les labels de champs
  champLabel: {
    color: "black",
    alignSelf: "flex-start", 
    fontSize: 15, 
    textTransform: "uppercase",
    marginBottom: 5,
    fontWeight: "bolder", 
  },
  // Style pour les champs de texte
  input: {
    width: "100%", 
    padding: 16, 
    fontSize: 15, 
    borderRadius: 10, 
    marginBottom: 20,
    backgroundColor: "#fafafa",
  },
  // Style pour le bouton de connexion
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
  // Style pour le texte du bouton
  textButton: {
    color: "black", 
    fontSize: 15, 
    fontWeight: "bolder", 
  },
  // Style pour le texte de création de compte
  textSignup: {
    marginTop: 20, 
    fontSize: 14, 
    color: "#666",
  },
  // Style pour le lien d'inscription
  link: {
    color: "#5784BA", 
    fontWeight: "bolder",
    textDecorationLine: "underline",
  },
});

