import React from 'react';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

import Register from './components/Register.tsx';
import Login  from './components/Login.tsx';
import Home  from './components/Home.tsx';
import Cart from './components/Cart.tsx';
import Nav from './components/Nav.tsx';
import OrderHistory from './components/OrderHistory.tsx';
import OrderSuccess from './components/OrderSuccess.tsx';
import { Provider } from "react-redux";
import store from './app/store.ts';
import Missing from './components/Missing.tsx';
import UpdateProfile from './components/UpdateProfile.tsx';

function App() {
  return (
    <Provider store={store}>
    <Router>
        <Routes>
          <Route path="/" element={<Register />}></Route>
          <Route path="/login" element={<Login />}></Route>
          <Route path="/home" element={<Home />}></Route>
          <Route path="/cart" element={<Cart />}></Route>
          <Route path="/nav" element={<Nav />}></Route>
          <Route path="/order-history" element={<OrderHistory />}></Route>
          <Route path="/order-success" element={<OrderSuccess />}></Route>
          <Route path="/update-profile" element={<UpdateProfile />}></Route>
          <Route path="/*" element={<Missing />}></Route>
        </Routes>
    </Router>
    </Provider>
  );
}

export default App;
