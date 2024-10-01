import { configureStore } from "@reduxjs/toolkit";
import tokenReducer from "../feature/redux/slice/tokenSlice.ts";

// const store= configureStore({
//   reducer: {
//     tokenLoader: tokenReducer,
//   },
// });

// export default store;

export default configureStore({
  reducer: {
    tokenLoader: tokenReducer,
  },
});

