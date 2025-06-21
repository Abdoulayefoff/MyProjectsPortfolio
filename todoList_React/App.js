import { useState } from 'react';
import Navigation from './Navigation/Navigation';
import { SessionContext, TodoListsContext } from './Context/Context';


export default function App() {
  
  const [session, setSession] = useState(null);// Définition d'un état pour gérer la session (inclus le token et le username)
  const [todoLists, setTodoLists] = useState(null);// Définition d'un état pour gérer les listes de Todo
  console.log('token', session);

  return (
    // Fourniture du contexte de session à tous les composants enfants
    <SessionContext.Provider value={[session, setSession]}>    
      <TodoListsContext.Provider value={[todoLists, setTodoLists]}>
        <Navigation />
      </TodoListsContext.Provider>
    </SessionContext.Provider>
  );
}

