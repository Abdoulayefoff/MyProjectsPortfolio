import React, { useEffect, useState, useContext } from "react";
import { View, Text, StyleSheet, TouchableOpacity, TextInput,Button,ScrollView,FlatList,} from "react-native";
import { ArrowLeftCircle, Trash2, Edit3 } from "react-native-feather";
import { SessionContext } from "../Context/Context";
import TodoItem from "../Components/TodoItem";
import {createTodo, getTodos, updateTodo, deleteTodo, } from "../js/todo";


//Ce composant gère l'ecran des todos Items avec les differents boutons
export default function ItemScreen({ route, navigation }) {

  // Récupération des paramètres de la route (id, title, toDeleteTodoList)
  const { id, title, toDeleteTodoList } = route.params; 
  const [session] = useContext(SessionContext);   // Récupération de la session depuis le contexte
  const [todoData, setTodoData] = useState([]); //etat pour stocker les todos récupérés
  const [temporaryTodos, setTemporaryTodos] = useState(todoData); 
  const [todoItem, setTodoItem] = useState(""); 
  const [error, setError] = useState(""); 
  const [countPourcentage, setCountPourcentage] = useState(0); 
  const [count, setCount] = useState(0); 
  const [countCompleted, setCountCompleted] = useState(0); 
  const [isEditingTitle, setIsEditingTitle] = useState(false); 
  const [newTitle, setNewTitle] = useState(title); 

   // Fonction pour récupérer les todos depuis l'API
  function loadTodos() {
    getTodos(id, session.token) 
      .then((response) => {
        setTodoData(response); 
        setTemporaryTodos(response); 
        const completedTasks = response.filter((item) => item.done).length; // on calcule le nombre de tâches complétées
        setCount(response.length); // mise à jour le nombre total de todos
        setCountCompleted(completedTasks); // mise à jour le nombre de tâches complétées
        setCountPourcentage((completedTasks / response.length) * 100); // mise à jour le pourcentage de complétion
      })
      .catch((err) => console.log(err.message));
    }

    function createTodoItem() {
      if (todoItem.length === 0) {
          setError("Ajoutez une tache SVP!");
          return; 
      }
      // Appelle la fonction pour créer la tâche avec les détails actuels
      createTodo(todoItem, id, session.token)
          .then((newTodo) => {
              // Ajoute la nouvelle tâche à la liste existante
              const updatedTodoItems = [...todoData, newTodo];
              setTodoData(updatedTodoItems);
              const completedItems = updatedTodoItems.filter((item) => item.done).length;
              const totalItems = updatedTodoItems.length;
              const completionPercentage = (completedItems / totalItems) * 100; // Mets à jour le pourcentage de complétion
              setCountPourcentage(completionPercentage);
              loadTodos();
              setTodoItem("");
              setError("");
          })
          .catch((err) => {
              console.log(err.message);
          });
    }

     // Fonction pour supprimer un todo
    function deleteTodoItem(id) {
      deleteTodo(id, session.token)
          .then(() => {
              // Filtre les éléments pour supprimer celui correspondant à l'id
              const updatedTodoItems = todoData.filter((item) => item.id !== id);
              setTodoData(updatedTodoItems);
              // Calcule le nombre de tâches terminées et le total, puis met à jour le pourcentage
              const completedTasks = updatedTodoItems.filter((item) => item.done).length;
              const totalTasks = updatedTodoItems.length;
              const completionPercentage = totalTasks > 0 ? (completedTasks / totalTasks) * 100 : 0;
              setCountPourcentage(completionPercentage);// Mets à jour le pourcentage de complétion
              loadTodos();
              setTodoItem("");
          })
          .catch((err) => {
              console.log(err.message);
          });
  }
  

    // Fonction pour mettre à jour l'état d'un todo
    function updateTodoItem(id, state, content) {
      updateTodo(id, state, session.token, content) 
        .then(() => loadTodos()) 
        .catch((err) => console.log(err.message)); 
    }

    // Fonction pour activer le mode édition du titre
    function onEditTodoListTitle() {
      setIsEditingTitle(true); 
    }
  
    // Fonction pour sauvegarder le nouveau titre
    function onSaveNewTitle() {
      setIsEditingTitle(false);
    }

    // useEffect pour récupérer les todos lors du montage du composant
    useEffect(() => {
      loadTodos();  // Appel de la fonction pour récupérer les todos
    }, []); 

    // Mettre à jour le nombre de tâches réalisées chaque fois que todoData change
    useEffect(() => {
      let completedCount = 0; 
      todoData.forEach((todo) => {
          if (todo.done) {
              completedCount++;
          }
      });
      setCountCompleted(completedCount); 
    }, [todoData]);

  return (
    
    <View>
      <View style={styles.container}>
        <ScrollView style={styles.containerCard}>
          {/* Header avec les boutons et le titre de la liste */}
          <View style={styles.header1}>
            <TouchableOpacity onPress={() => navigation.navigate("Todolists", { screen: "Todolist" })}>
              <ArrowLeftCircle stroke="black" fill="#fff" width={32} height={32} />
            </TouchableOpacity>

            {isEditingTitle ? (
              <TextInput
                style={styles.input}
                value={newTitle}
                onChangeText={(text) => setNewTitle(text)}
                onBlur={onSaveNewTitle}
                autoFocus
              />
            ) : (
              <Text style={styles.header2}>{newTitle}</Text>
            )}

            <TouchableOpacity onPress={onEditTodoListTitle}>
              <Edit3 stroke="#392E2C" fill="#fff" width={25} height={25} />
            </TouchableOpacity>

            <TouchableOpacity onPress={() => { toDeleteTodoList(id); navigation.navigate("Todolists", { screen: "Todolist" }); }}>
              <Trash2 stroke="#A7001E" fill="#fff" width={25} height={25} />
            </TouchableOpacity>
          </View>

          {/* Barre de progression */}
          <View style={styles.barProgression}>
            <View style={{ ...styles.bar, width: `${countPourcentage}%` }}>
              <Text style={styles.pourcentage}>{Math.round(countPourcentage)}%</Text>
            </View>
          </View>

          {/* Options d'action pour la liste */}
          <View style={styles.allButtonContainer}>
            <View style={styles.optionButton}><Button title={`Show All(${count})`} onPress={loadTodos} /></View>
            <View style={styles.optionButton}><Button title={`Show Incomplete(${count - countCompleted})`} onPress={() => setTodoData(temporaryTodos.filter((item) => !item.done))} /></View>
            <View style={styles.optionButton}><Button title={`Show Completed(${countCompleted})`} onPress={() => setTodoData(temporaryTodos.filter((item) => item.done))} /></View>
            <View style={styles.optionButton}><Button title="CheckAll" onPress={() => todoData.forEach((item) => updateTodoItem(item.id, true, item.content))} /></View>
            <View style={styles.optionButton}><Button title="UncheckAll" onPress={() => todoData.forEach((item) => updateTodoItem(item.id, false, item.content))} /></View>
          </View>

          {/* Section pour ajouter un nouveau todo */}
          <View style={styles.itemContainer}>
            <TextInput
              style={styles.input}
              value={todoItem}
              placeholder="Ajouter une tâche !"
              onChangeText={(value) => setTodoItem(value)}
            />
            <View style={styles.buttonAjout}>
              <Button title="Cliquez pour ajouter" onPress={createTodoItem} />
            </View>
            <Text style={{ color: "red", marginVertical: 10 }}>{error}</Text>
          </View>

          {/* Liste des todos */}
          <View style={styles.listContainer}>
            {todoData.length > 0 ? (
              <FlatList
                data={todoData}
                renderItem={({ item }) => (
                  <TodoItem
                    item={item}
                    deleteTodoItem={deleteTodoItem}
                    updateTodoItem={updateTodoItem}
                    loadTodos={loadTodos}
                    navigation={navigation}
                  />
                )}
              />
            ) : (
              <Text style={styles.description}>Votre liste est vide !</Text>
            )}
          </View>
        </ScrollView>
      </View>
      </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1, 
    justifyContent: "center", 
    alignItems: "center", 
  },

  header1: {
    flexDirection: "row", 
    alignItems: "center", 
    justifyContent: "space-between", 
    paddingVertical: 10, 
  },

  containerCard: {
    width: "80%", 
    backgroundColor: "#ffffff", 
    padding: 20,
    borderRadius: 10, 
    marginVertical: 30, 
    shadowColor: "#000", 
  },

  header2: {
    textTransform: "uppercase", 
    fontSize: 27, 
    fontWeight: "bolder", 
    color: "black", 
    textAlign: "center", 
  },

  input: {
    padding: 8,
    fontSize: 16, 
    borderBottomWidth: 3, 
    borderColor: "black",
    marginHorizontal: 10, 
    flex: 1, 
  },

  itemContainer: {
    flexDirection: "row",
    alignItems: "center", 
    marginVertical: 20, 
  },

  buttonAjout: {
    paddingVertical: 10,
    paddingHorizontal: 15, 
    borderRadius: 8,
  },

  barProgression: {
    marginVertical: 20, 
    width: '100%', 
    height: 23,
    borderRadius: 11, 
    overflow: 'hidden', 
    backgroundColor: '#D5CABC',
    
  },

  bar: {
    height: '100%', 
    backgroundColor: '#317AC1', 
    justifyContent: 'center', 
    alignItems: 'center', 
    borderRadius: 10, 
  },

  pourcentage: {
    color: '#F0F0F0', 
    fontWeight: 'bolder', 
    fontSize: 20, 
  },

  description: {
    fontSize: 18, 
    color: "#666", 
    textAlign: "center", 
  },

  listContainer: {
    flex: 1, 
    paddingVertical: 10, 
  },

  allButtonContainer: {
    flexDirection: "row", 
    justifyContent: "space-around", 
    marginVertical: 15, 
  },

  optionButton: {
    backgroundColor: "white", 
    paddingVertical: 10, 
    paddingHorizontal: 15, 
    borderRadius: 8,
  },
});
