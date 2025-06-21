import React, { useState } from "react";
import {TouchableOpacity, StyleSheet, Text, View, Image,} from "react-native";


//Composant TodoList, pour afficher une liste de taches
export default function TodoList(props) {
  
  const { title, username, id } = props.todoList; 
  const [hovered, setHovered] = useState(false); //pour gerer le survol d'une carte
  
  // Fonction pour naviguer vers l'écran de la liste de tâches
  const navigateToItemScreen = () => {
    props.navigationProp.navigate("Todolists", {
      screen: "ItemScreen",
      params: {
        id,
        title,
        username,
        toDeleteTodoList: props.toDeleteTodoList,
      },
    });
  };

  return (
    <TouchableOpacity onPress={navigateToItemScreen}>
      <View
        style={[
          styles.container,
          {
            backgroundColor: hovered ? "#F99F72" : "white",
          },
        ]}
        onMouseEnter={() => setHovered(true)}  // Déclenche l'effet de survol lorsque la souris entre dans la carte
        onMouseLeave={() => setHovered(false)} // Annule l'effet lorsque la souris quitte la carte
      >
        <Text
          style={[
            styles.text,
            { color: hovered ? "black" : "#D6955B" },
          ]}
        >
          {title}
        </Text>
      </View>
    </TouchableOpacity>
  );
}

const styles = StyleSheet.create({
  container: {
    width: 500, 
    height: 200, 
    paddingHorizontal: 20,
    margin: 20, 
    justifyContent: "center",
    alignItems: "center", 
    transition: "all 0.3s ease-in-out", // Transition de 0.3 secondes (effet de survol)
  },
  text: {
    fontSize: 30, 
    fontWeight: "bolder", 
    marginTop: 10, 
    marginLeft: 12, 
    textTransform: "uppercase", 
  },
  
});


