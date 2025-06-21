import React from "react";
import SignInScreen from "../Screen/SignInScreen";
import HomeScreen from "../Screen/HomeScreen";
import SignUp from "../Components/SignUp";
import { NavigationContainer } from "@react-navigation/native";
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import { SessionContext } from "../Context/Context";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import SignoutScreen from "../Screen/SignoutScreen";
import TodoListNavigator from "./TodoListNavigator";
import { Text, View, StyleSheet } from "react-native";

const Tab = createBottomTabNavigator();
const Stack = createNativeStackNavigator();

export default function Navigation() {
  return (
    <SessionContext.Consumer>
      {([session, setSession]) => (
        <NavigationContainer>
          {session == null ? (
            // Affiche les écrans SignIn et SignUp si l'utilisateur n'est pas connecté
            <Tab.Navigator
              screenOptions={{
                tabBarStyle: { display: "none" },
                headerShown: false,
              }}
            >
              <Tab.Screen 
                name="SignIn" 
                component={SignInScreen} 
                options={{
                  title: "Connexion",
                  tabBarLabel: () => <Text style={styles.tabLabel}>Connexion</Text>,
                }}
              />
              <Tab.Screen 
                name="SignUp" 
                component={SignUp} 
                options={{
                  title: "Inscription",
                  tabBarLabel: () => <Text style={styles.tabLabel}>Inscription</Text>,
                }}
              />
            </Tab.Navigator>
          ) : (
            // Affiche les onglets principaux si l'utilisateur est connecté
            <Tab.Navigator
              initialRouteName="Home"
              screenOptions={{
                tabBarActiveTintColor: "#6200EE",
                tabBarInactiveTintColor: "gray",
                tabBarStyle: styles.tabBar,
                headerShown: false,
              }}
            >
              <Tab.Screen 
                name="Home" 
                component={HomeScreen} 
                options={{
                  title: "Accueil",
                  tabBarLabel: () => (
                    <View style={styles.tabItem}>
                      <Text style={styles.tabText}>🏠 Accueil</Text>
                    </View>
                  ),
                }}
              />
              <Tab.Screen 
                name="Todolists" 
                component={TodoListNavigator} 
                options={{
                  title: "Mes Listes",
                  tabBarLabel: () => (
                    <View style={styles.tabItem}>
                      <Text style={styles.tabText}>📋 Mes Listes</Text>
                    </View>
                  ),
                }}
              />
              <Tab.Screen 
                name="Signout" 
                component={SignoutScreen} 
                options={{
                  title: "Déconnexion",
                  tabBarLabel: () => (
                    <View style={styles.tabItem}>
                      <Text style={styles.tabText}>🚪 Déconnexion</Text>
                    </View>
                  ),
                }}
              />
            </Tab.Navigator>
          )}
        </NavigationContainer>
      )}
    </SessionContext.Consumer>
  );
}

const styles = StyleSheet.create({
  tabBar: {
    backgroundColor: "#fff", 
    height: 60,
    paddingBottom: 8, 
    borderTopWidth: 0.5, 
    borderTopColor: "#ddd", 
  },
  tabItem: {
    alignItems: "center", 
  },
  tabText: {
    fontSize: 14, 
    color: "gray",
  },
  tabLabel: {
    fontSize: 14,
    color: "#6200EE",
    fontWeight: "bold",
  },
});

