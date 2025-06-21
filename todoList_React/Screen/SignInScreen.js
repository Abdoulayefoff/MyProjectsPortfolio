import React from "react";
import { View, StyleSheet } from "react-native";
import SignIn from "../Components/SignIn";

export default function SignInScreen() {
  return (
    <View style={styles.container}>
      <SignIn />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  }
});

