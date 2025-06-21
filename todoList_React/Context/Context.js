import React from "react";
export const UsernameContext = React.createContext();
export const SessionContext = React.createContext(); //on cree un contexte pour la session,elle stocke le jeton d'information et le username
export const TodoListsContext = React.createContext();//on cree aussi un contexte pour les listes de taches,pour faciliter le partage des listes entre differents composants

