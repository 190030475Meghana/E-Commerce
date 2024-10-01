import React from "react";
import axios from "axios";
import { useSelector } from "react-redux";

interface Product {
  productId: number;
  name: string;
  description: string;
  price: number;
}

interface ProductCardProps {
  product: Product;
}

const ProductCard: React.FC<ProductCardProps> = ({ product }) => {
  const { productId, name, description, price } = product;
  const authToken = useSelector((state: any) => state.tokenLoader.token);
  const userId = useSelector((state: any) => state.tokenLoader.userId);
  console.log(authToken);
  console.log(userId);

  const handleAddToCart = async () => {
    //const userId = 1;
    const cartItem = {
      productId: product.productId,
      quantity: 1,
      name: product.name,
      description: product.description,
      price: product.price,
    };

    const response = await axios.post(
      `http://localhost:6789/api/cart/add/${userId}`,
      cartItem
    );
    alert("Added to cart successfully!");
  };

  return (
    <div className="product-card">
      <h3>{name}</h3>
      <p>{description}</p>
      <p>Price: Rs. {price}</p>
      <button onClick={() => handleAddToCart()}>Add to Cart</button>
    </div>
  );
};

export default ProductCard;
