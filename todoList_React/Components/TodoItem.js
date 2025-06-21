import React, { useState, useEffect, useContext } from "react"; 
import {TouchableOpacity, StyleSheet, Button, Text, View, TextInput,Switch,} from "react-native";
import { SessionContext } from "../Context/Context"; 
import { updateTodo } from "../js/todo"; 
import { Trash2, Edit3 } from "react-native-feather"; 

export default function TodoItem(props) {
  const { item, updateTodoItem, deleteTodoItem, loadTodos } = props; // Déstructuration des props
  const [session,setSession] = useContext(SessionContext); // Récupération de la session depuis le contexte
  const [content, setContent] = useState(item.content); // un état pour le contenu de la tâche
  const [done, setDone] = useState(item.done); 
  const [isEditing, setIsEditing] = useState(false);  
  const [error, setError] = useState(""); 

  

  // Fonction pour gérer le changement d'état de la tâche
  const handleSwitchChange = (newState) => {
    setDone(newState);
    updateTodoItem(item.id, newState, content); // on apppelle la fonction pour mettre à jour la tâche
  };

  const handleContentChange = (value) => setContent(value); // on met à jour le contenu de la tâche

  //une fonction pour mettre à jour la tâche après modification
  const handleUpdateTodoItem = () => {
    if (!content.trim()) { // on verifie que le contenu n'est pas vide
      setError("Votre modification n'est pas valide");
      return;
    }

    updateTodo(item.id, done, session.token, content) 
      .then(() => {
        setIsEditing(false); 
        loadTodos();
      })
      .catch((err) => setError(err.message)); 
  };

  // Synchroniser l'état 'done' chaque fois que 'item.done' change
  useEffect(() => {
    setDone(item.done); 
  }, [item.done]);

  return (
    <View style={styles.container}>
      {!isEditing ? ( 
        <View style={styles.content}>
          {/* Switch pour marquer la tâche comme terminée ou non avec une couleur dépendante de l'état */}
          <Switch
            trackColor={{ false: "#D5CABC", true: "#4CAF50" }} 
            thumbColor={done ? "4CAF50" : "white"} 
            value={done}
            onValueChange={handleSwitchChange} 
          />
          {/* Texte de la tâche, barré si terminée */}
          <Text style={[styles.text, done && styles.completed]}>{content}</Text>
          <View style={styles.action}>
            {/* Icône d'édition */}
            <TouchableOpacity style={styles.icon} onPress={() => setIsEditing(true)}>
              <Edit3 stroke="#392E2C" width={24} height={24} /> 
            </TouchableOpacity>
            {/* Icône de suppression */}
            <TouchableOpacity style={styles.icon} onPress={() => deleteTodoItem(item.id)}>
              <Trash2 stroke="#A7001E" width={24} height={24} /> 
            </TouchableOpacity>
          </View>
        </View>
      ) : (
        <View style={styles.editContainer}>
          {/* Zone de saisie pour modifier le contenu */}
          <TextInput
            style={styles.input}
            value={content}
            onChangeText={handleContentChange}
          />
          <Button title="Modifier votre tache !" onPress={handleUpdateTodoItem} /> 
          {/* Affiche l'erreur si présente */}
          {error ? <Text style={styles.errorText}>{error}</Text> : null}
        </View>
      )}
      {/* Ligne de séparation */}
      <View style={styles.line} />
    </View>
  );
}

const styles = StyleSheet.create({
  // Style du conteneur principal de chaque tâche
  container: {
    marginVertical: 10, 
    padding: 10, 
    borderRadius: 10,
  },

  // Style du contenu principal de la tâche
  content: {
    flexDirection: "row", 
    alignItems: "center",
  },

  // Style du conteneur d'action pour les icônes d'édition et de suppression
  action: {
    flexDirection: "row", 
    justifyContent: "flex-end", 
    flex: 1, 
  },

  // Style des icônes d'action
  icon: {
    marginLeft: 15,
  },

  // Style du texte de la tâche
  text: {
    fontSize: 18,
    marginLeft: 10, 
    color: "black",
    fontWeight: "bolder",
  },

  // Style pour le texte barré si la tâche est terminée
  completed: {
    textDecorationLine: "line-through", 
    color: "#335F8A",
  },

  // Style du conteneur de la zone d'édition
  editContainer: {
    flexDirection: "row", 
    alignItems: "center", 
    marginVertical: 11,
  },

  // Style de la zone de saisie de texte
  input: {
    flex: 1,
    padding: 12, 
    fontSize: 16,
    borderWidth: 2, 
    borderRadius: 8, 
    marginRight: 10, 
  },

  // Style du texte d'erreur
  errorText: {
    color: "#FF3B30", 
    marginTop: 5, 
    fontSize: 14, 
  },

  // Style de la ligne de séparation entre les éléments
  line: {
    height: 1, 
    backgroundColor: "#E0E0E0", 
    marginVertical: 10, 
  },
});

