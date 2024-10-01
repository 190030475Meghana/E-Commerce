import React, { useState, useEffect } from "react";
import axios from "axios";
import Nav from "./Nav.tsx";
import "../style/OrderHistory.css";
import { useSelector } from "react-redux";

interface Order {
  orderId: number;
  userId: number;
  productId: number;
  createdAt: string;
  productName: string;
  productDescription: string;
  productPrice: number;
}

const OrderHistory: React.FC = () => {
  const [orders, setOrders] = useState<Order[]>([]);
  const authToken = useSelector((state: any) => state.tokenLoader.token);
  const userId = useSelector((state: any) => state.tokenLoader.userId);

  useEffect(() => {
    fetchOrderHistory();
  }, []);

  const fetchOrderHistory = async () => {
    try {
      //const userId = 1;
      const response = await axios.get(
        `http://localhost:6789/api/orders/${userId}`
      );
      if (response.status === 200) {
        setOrders(response.data);
      } else {
        console.error("Failed to fetch order history");
      }
    } catch (error) {
      console.error("Error fetching order history:", error);
    }
  };

  return (
    <div>
      <Nav />
      <div className="order-history">
        <h1>Order History</h1>
        <div className="order-list">
          {orders.length === 0 ? (
            <p>No orders found.</p>
          ) : (
            orders.map((order) => (
              <div key={order.orderId} className="order-card">
                <h3>Order ID: {order.orderId}</h3>
                <p>Name: {order.productName}</p>
                <p>Description: {order.productDescription}</p>
                <p>Price: Rs. {order.productPrice}</p>
                <p>Order Date: {order.createdAt}</p>
                <hr />
              </div>
            ))
          )}
        </div>
      </div>
    </div>
  );
};

export default OrderHistory;
