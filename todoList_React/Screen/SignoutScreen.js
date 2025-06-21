import React, { useContext, useState } from "react";
import { View, Text, ImageBackground, StyleSheet, TouchableOpacity, ActivityIndicator, Dimensions } from "react-native";
import { SessionContext } from "../Context/Context";
const { width, height } = Dimensions.get("window");

//composant pour se déconnecter
export default function SignoutScreen({ navigation }) {
  const [session, setSession] = useContext(SessionContext);
  const [loading, setLoading] = useState(false);

  // Fonction de déconnexion
  function getDisconnect() {
    setLoading(true);
    setTimeout(() => {
      setSession(null); // on efface la session
      navigation.push("signin"); // on redirige vers l'écran de connexion
    }, 1500); // Simuler un délai de déconnexion
  }

  return (
    <ImageBackground source={require("../assets/bgSignn.png")} style={styles.background}>
      <View style={styles.container}>
        <View style={styles.containerCard}>
          <Text style={styles.header}>Au revoir {session.username} et à bientôt !</Text>
          <Text style={styles.slogan}>"Restez organisé, restez productif!"</Text>

          <View style={styles.buttonContainer}>
            {loading ? (
              <ActivityIndicator size="large" color="#0070D0" />
            ) : (
              <>
                <TouchableOpacity style={styles.buttonDeconnecte} onPress={getDisconnect}>
                  <Text style={styles.logoutText}>Se déconnecter</Text>
                </TouchableOpacity>

                <TouchableOpacity style={styles.buttonRetour} onPress={() => navigation.goBack()}>
                  <Text style={styles.backText}>Accueil</Text>
                </TouchableOpacity>
              </>
            )}
          </View>
        </View>
      </View>
    </ImageBackground>
  );
}

const styles = StyleSheet.create({
  background: {
    width: "100%",
    height: "100%",
    justifyContent: "center",
    alignItems: "center",
  },
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
  containerCard: {
    width: width / 2, 
    height: height / 2,
    backgroundColor: "rgba(255, 255, 255, 0.7)",
    padding: 20,
    borderRadius: 20,
    alignItems: "center",
    justifyContent: "space-between", 
  },
  header: {
    fontSize: 22,
    fontWeight: "600",
    color: "#333",
    textAlign: "center",
  },
  slogan: {
    fontSize: 16,
    fontStyle: "italic",
    color: "#555",
    textAlign: "center",
  },
  buttonContainer: {
    width: "100%",
    alignItems: "center",
  },
  buttonDeconnecte: {
    backgroundColor: "#A7001E",
    paddingVertical: 12,
    paddingHorizontal: 30,
    borderRadius: 5,
    marginBottom: 10,
    width: "90%",
    alignItems: "center",
  },
  logoutText: {
    color: "white",
    fontSize: 18,
    fontWeight: "bold",
  },
  buttonRetour: {
    backgroundColor: "#5784BA",
    paddingVertical: 12,
    paddingHorizontal: 30,
    borderRadius: 5,
    width: "90%",
    alignItems: "center",
  },
  backText: {
    color: "white",
    fontSize: 16,
  },
});
