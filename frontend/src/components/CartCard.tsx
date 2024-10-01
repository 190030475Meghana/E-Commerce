import React from "react";
import axios from "axios";

interface Product {
  productId: number;
  quantity: number;
  name: string;
  description: string;
  price: number;
}

interface CartCardProps {
  product: Product;
  onDelete: (productId: number) => void;
}

const CartCard: React.FC<CartCardProps> = ({ product, onDelete }) => {
  const { productId, quantity, name, description, price } = product;

  const handleDeleteItem = async () => {
    try {
      const response = await axios.delete(
        `http://localhost:6789/api/cart/1/remove/${productId}`
      );
      if (response.status === 200) {
        onDelete(productId);
      } else {
        console.error("Failed to delete product. Status:", response.status);
      }
    } catch (error) {
      console.error("Error deleting product:", error);
    }
  };

  return (
    <div className="product-card">
      <h3>{name}</h3>
      <p>{description}</p>
      <p>Quantity: {quantity}</p>
      <p>Price: Rs. {price}</p>
      <button onClick={handleDeleteItem}>Delete</button>
    </div>
  );
};

export default CartCard;
