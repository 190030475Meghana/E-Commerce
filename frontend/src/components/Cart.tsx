// import React, { useState, useEffect } from "react";
// import { useNavigate } from "react-router-dom";
// import CartCard from "./CartCard.tsx";
// import Nav from "./Nav.tsx";
// import "../style/Home.css";

// interface Product {
//   userId: number;
//   productId: number;
//   quantity: number;
//   name: string;
//   description: string;
//   price: number;
// }

// const Cart = () => {
//   const [products, setProducts] = useState<Product[]>([]);
//   const navigate = useNavigate();

//   useEffect(() => {
//     fetchProducts(); // Fetch products when component mounts
//   }, []);

//   const fetchProducts = async () => {
//     try {
//       const userId = 1;
//       const response = await fetch(`http://localhost:6789/api/cart/${userId}`);
//       if (!response.ok) {
//         throw new Error("Network response was not ok");
//       }
//       const data: Product[] = await response.json();
//       setProducts(data); // Set products state with fetched data
//     } catch (error) {
//       console.error("Error fetching products:", error);
//     }
//   };

//   const handleDeleteProduct = (productId: number) => {
//     const updatedProducts = products.filter(
//       (product) => product.productId !== productId
//     );
//     setProducts(updatedProducts);
//   };

//   return (
//     <div className="app">
//       <Nav />
//       <h1>Product List</h1>
//       <div className="product-list">
//         {products.map((product) => (
//           <CartCard
//             key={product.productId}
//             product={product}
//             onDelete={handleDeleteProduct}
//           />
//         ))}
//       </div>
//       <br></br>
//       <br></br>
//       {/* <button onClick={handleCheckout}>Checkout</button> */}
//       <div>
//         <button className="checkout-button">Checkout</button>
//       </div>
//     </div>
//   );
// };

// export default Cart;

//=====================================================================================================================================

import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import CartCard from "./CartCard.tsx";
import Nav from "./Nav.tsx";
import "../style/Home.css";
import { useSelector } from "react-redux";

interface Product {
  userId: number;
  productId: number;
  quantity: number;
  name: string;
  description: string;
  price: number;
}

const Cart = () => {
  const [products, setProducts] = useState<Product[]>([]);
  const [totalCost, setTotalCost] = useState<number>(0);
  const navigate = useNavigate();
  const authToken = useSelector((state: any) => state.tokenLoader.token);
  const userId = useSelector((state: any) => state.tokenLoader.userId);
  console.log(authToken);
  console.log(userId);

  useEffect(() => {
    console.log("Auth Token:", authToken);
    console.log("User ID:", userId);
    if (!authToken || !userId) {
      return;
    }
    fetchProducts();
  }, [authToken, userId]);

  useEffect(() => {
    calculateTotalCost();
  }, [products]);

  const fetchProducts = async () => {
    try {
      //const userId = 1;
      const response = await fetch(`http://localhost:6789/api/cart/${userId}`, {
        headers: {
          Authorization: `Bearer ${authToken}`,
        },
      });
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      const data: Product[] = await response.json();
      setProducts(data);
    } catch (error) {
      console.error("Error fetching products:", error);
    }
  };

  const handleDeleteProduct = (productId: number) => {
    const updatedProducts = products.filter(
      (product) => product.productId !== productId
    );
    setProducts(updatedProducts);
  };

  const calculateTotalCost = () => {
    let total = 0;
    products.forEach((product) => {
      total += product.quantity * product.price;
    });
    setTotalCost(total);
  };

  const handleCheckout = async () => {
    try {
      //const userId = 1;
      const productIds = products.map((product) => product.productId);
      const response = await fetch(
        `http://localhost:6789/api/orders/create/${userId}`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(productIds),
        }
      );
      if (!response.ok) {
        throw new Error("Failed to create order");
      }
      navigate("/order-success");
    } catch (error) {
      console.error("Error creating order:", error);
    }
  };

  return (
    <div className="app">
      <Nav />
      <h1>Cart</h1>
      <div className="product-list">
        {products.map((product) => (
          <CartCard
            key={product.productId}
            product={product}
            onDelete={handleDeleteProduct}
          />
        ))}
      </div>
      <div className="total-cost">
        <h2>Total Cost: Rs. {totalCost.toFixed(2)}</h2>
      </div>
      <div>
        <button className="checkout-button" onClick={handleCheckout}>
          Checkout
        </button>
      </div>
    </div>
  );
};

export default Cart;
