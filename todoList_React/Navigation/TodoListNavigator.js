import React from "react"; 
import { createNativeStackNavigator } from "@react-navigation/native-stack"; 
import ItemScreen from "../Screen/ItemScreen";
import ListScreen from "../Screen/ListScreen";

const Stack = createNativeStackNavigator();

//ce composant gère la navigation entre les ecrans ItemScreen et ListScreen
export default function TodoListNavigator() {
  return (
   
    <Stack.Navigator initialRouteName="Todolist">
      
      {/* Définition du premier écran ListScreen */}
      <Stack.Screen
        name="Todolist"
        component={ListScreen}
        options={{ headerShown: false }} // Masquer l'en-tête pour cet écran
      />

      {/* Définition du deuxième écran ItemScreen */}
      <Stack.Screen
        name="ItemScreen"
        component={ItemScreen} 
        options={{ headerShown: false }}
      />

    </Stack.Navigator>
  );
}

