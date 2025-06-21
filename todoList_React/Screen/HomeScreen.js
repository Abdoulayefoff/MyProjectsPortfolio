
import React from "react";
import { View, Text, StyleSheet, TouchableOpacity,ImageBackground,Image, } from "react-native";

export default function HomeScreen({ navigation }) {
  return (
    <ImageBackground
      source={require("../assets/bgSignn.png")} 
      style={styles.backgroundImage}  
    >
      <View style={styles.container}>
        <View style={styles.card}>
          <Image
            source={require("../assets/images.png")}
            style={styles.image}  
          />
          <Text style={styles.header}>
            Bonjour et bienvenue dans votre gestionnaire de tâche !
          </Text>
          <Text style={styles.slogan}>
            "Organisez vos tâches, maîtrisez votre quotidien !" 
          </Text>
          <TouchableOpacity
            style={styles.button} 
            onPress={() => {
              navigation.navigate("Todolists", {
                screen: "Todolist",
              });
            }}
          >
            <Text style={styles.buttonText}>Commencer</Text>
          </TouchableOpacity>
        </View>
      </View>
    </ImageBackground>
  );
}

const styles = StyleSheet.create({
  // Style de l'arrière-plan
  backgroundImage: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },

  // Conteneur principal
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },

  // Style de la carte contenant l'image et le texte
  card: {
    width: "100%",  
    height: "50%", 
    backgroundColor: "rgba(255, 255, 255, 0.85)",  // Fond blanc semi-transparent
    paddingVertical: 40,
    paddingHorizontal: 40,
    textAlign: "center",
    borderRadius: 20, 
    alignItems: "center",
  },

  // Style du titre
  header: {
    fontSize: 24, 
    fontWeight: "bolder", 
    color: "black", 
    marginVertical: 20,  
    textAlign: "center", 
  },

  // Style du slogan
  slogan: {
    fontSize: 18,  
    fontWeight: "400",  
    color: "#666",  
    marginBottom: 30,  
    fontStyle: "italic",  
    textAlign: "center",
  },

  // Style de l'image
  image: {
    width: 300,
    height: 160,
    marginBottom: 20, 
  },

  // Style du bouton
  button: {
    backgroundColor: "#76CDCD",  
    paddingVertical: 12,
    paddingHorizontal: 40,
    borderRadius: 25, 
    marginTop: 30,  
    alignItems: "center", 
    elevation: 3, 
    textTransform: "uppercase",
  },

  // Style du texte dans le bouton
  buttonText: {
    color: "black",  
    fontSize: 18,  
    fontWeight: "bolder", 
  },
});

