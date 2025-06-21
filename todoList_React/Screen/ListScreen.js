import React, { useEffect, useState, useContext } from "react";
import { View, Text, StyleSheet, Pressable, TextInput, FlatList, ImageBackground } from "react-native";
import TodoList from "../Components/TodoList";
import { SessionContext, TodoListsContext } from "../Context/Context";
import { getTodoLists, deleteTodoList, createTodoList } from "../js/todoList";

// Ce composant affiche l'écran des listes de tâches
export default function ListScreen({ navigation }) {
  const [session, setSession] = useContext(SessionContext); // Utilisation du contexte pour gérer la session utilisateur
  const [todoLists, setTodoLists] = useContext(TodoListsContext); // Utilisation du contexte pour stocker les listes de tâches
  const [todoList, setTodoList] = useState(""); // État pour la nouvelle liste de tâches à ajouter
  const [list, setList] = useState([]); 
  const [error, setError] = useState(""); 

  // Fonction pour récupérer les listes de tâches via l'API
  function loadTodoList() {
    getTodoLists(session.username, session.token)
      .then((response) => {
        setTodoLists(response); 
        setList(response); 
      })
      .catch((err) => console.log(err.message)); 
  }

  // Fonction pour créer une nouvelle liste de tâches
  function toCreateTodoList() {
    if (todoList.length === 0) { 
      setError("Ajoutez une liste svp !");
    } else {
      setError("");
      createTodoList(session.username, todoList, session.token)
        .then(() => {
          loadTodoList(); 
          setTodoList(""); 
          setError(""); 
        })
        .catch((err) => console.log(err.message));
    }
  }

  // Fonction pour supprimer une liste de tâches
  function toDeleteTodoList(id) {
    deleteTodoList(id, session.token)
      .then(() => loadTodoList()) 
      .catch((err) => console.log(err.message));
  }

  // Charger les listes de tâches au chargement du composant
  useEffect(() => {
    loadTodoList(); // Appeler la fonction pour charger les listes de tâches
  }, []);

  
  return (
    <ImageBackground
      source={require("../assets/bgSign.jpg")}
      style={styles.backgroundImage} 
      resizeMode="cover"
    > 
      <View style={styles.container}>
        
        <View style={styles.addItemContainer}> 
          <View style={styles.groupInput}> 
            
            <TextInput
              style={styles.input} 
              value={todoList}
              placeholder="Créez votre liste de tâches et réalisez vos projets avec succès ! "
              onChangeText={(value) => setTodoList(value)}
            />
            
            <Pressable style={styles.buttonAjout} onPress={toCreateTodoList}> {/* Bouton Ajouter */}
              <Text style={styles.ajoutListButton}>Ajouter votre liste</Text>
            </Pressable>
          </View>
          
          {error ? <Text style={styles.errorText}>{error}</Text> : null} 
        </View>

        <View style={styles.descriptionContainer}> {/* Conteneur pour les listes de tâches */}
          {list.length > 0 ? (
            <FlatList
              data={todoLists} // Données pour la liste
              horizontal={true} // Affichage horizontal
              contentContainerStyle={styles.listContent} 
              renderItem={({ item }) => (
                <TodoList
                  todoList={item} 
                  toDeleteTodoList={toDeleteTodoList}
                  navigationProp={navigation}
                />
              )}
              keyExtractor={(item) => item.id.toString()} 
            />
          ) : (
            <Text style={styles.description}>Vous n'avez aucune liste de tâches !</Text>
          )}
        </View>
      </View>
    </ImageBackground>
  );
}

const styles = StyleSheet.create({
  backgroundImage: {
    flex: 1, 
    width: "100%",
    height: "100%",
    justifyContent: "center", 
  },
  container: {
    flex: 1, 
    padding: 20,
    //backgroundColor: "rgba(255, 255, 255, 0.3)", 
    borderRadius: 10, 
  },
  addItemContainer: {
    padding: 20,
    width: "100%",
    backgroundColor: "#ffffff",
    borderRadius: 10, 
  },
  groupInput: {
    flexDirection: "row", 
    alignItems: "center", 
    borderRadius: 8, 
  },
  input: {
    flex: 1, 
    padding: 12, 
    fontSize: 16, 
    borderWidth: 2,
    borderColor: "#ddd", 
    borderRadius: 8,
    backgroundColor: "#ffffff",
    marginRight: 10, 
    color: "#333", 
  },
  buttonAjout: {
    paddingVertical: 10, 
    paddingHorizontal: 15, 
    backgroundColor: "black",
    borderRadius: 8, 
  },
  ajoutListButton: {
    color: "#fff", 
    fontWeight: "bold", 
    fontSize: 16,
  },
  errorText: {
    color: "red", 
    fontSize: 14,
    marginTop: 5,
    alignSelf: "center",
  },
  descriptionContainer: {
    display: "flex",
    alignItems: "center", 
    marginVertical: 20,
  },
  description: {
    fontSize: 20, 
    color: "#333", 
    fontWeight: "600", 
  },
  listContent: {
    flexDirection: 'row', 
    width: '100%', 
    flexWrap: 'wrap', 
    justifyContent: "center",
  },
});
