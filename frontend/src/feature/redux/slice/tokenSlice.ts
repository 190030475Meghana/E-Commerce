import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  token: "",
  userId: "",
  username:""
};

export const tokenSlice = createSlice({
  name: "tokenLoader",
  initialState,
  reducers: {
    setSavedToken: (state, action) => {
      state.token = action.payload.token;
      state.userId = action.payload.userId;
      state.username=action.payload.username;
    },
    clearSavedToken: (state) => {
      state.token = "";
      state.userId = "";
      state.username = "";
    }
  },
});

export const { setSavedToken , clearSavedToken } = tokenSlice.actions;

export default tokenSlice.reducer;
