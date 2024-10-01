// import React, { useState, useEffect } from "react";
// import { CgProfile } from "react-icons/cg";
// import { RxHamburgerMenu } from "react-icons/rx";
// import { FaCartShopping } from "react-icons/fa6";
// import "../style/Home.css";
// import ProductCard from "./ProductCard.tsx";
// import { useNavigate } from "react-router-dom";
// import Nav from "./Nav.tsx";

// interface Product {
//   productId: number;
//   name: string;
//   description: string;
//   price: number;
// }

// const Home = () => {
//   const [products, setProducts] = useState<Product[]>([]);
//   const navigate = useNavigate();
//   useEffect(() => {
//     fetchProducts();
//   }, []);

//   const fetchProducts = async () => {
//     try {
//       const response = await fetch("http://localhost:6789/api/products/all");
//       if (!response.ok) {
//         throw new Error("Network response was not ok");
//       }
//       const data: Product[] = await response.json();
//       setProducts(data); // Set products state with fetched data
//     } catch (error) {
//       console.error("Error fetching products:", error);
//     }
//   };

//   return (
//     <div>
//       <Nav />
//       <h1 style={{ textAlign: "center" }}>Product List</h1>
//       <div className="app">
//         <div className="product-list">
//           {products.map((product) => (
//             <ProductCard key={product.productId} product={product} />
//           ))}
//         </div>
//       </div>
//     </div>
//   );
// };

// export default Home;

import React, { useState, useEffect } from "react";
import { CgProfile } from "react-icons/cg";
import { RxHamburgerMenu } from "react-icons/rx";
import { FaCartShopping } from "react-icons/fa6";
import "../style/Home.css";
import ProductCard from "./ProductCard.tsx";
import { useNavigate } from "react-router-dom";
import Nav from "./Nav.tsx";
import { useSelector } from "react-redux";

interface Product {
  productId: number;
  name: string;
  description: string;
  price: number;
}

const Home = () => {
  const [products, setProducts] = useState<Product[]>([]);
  const [searchTerm, setSearchTerm] = useState<string>("");
  //   const authToken = useSelector((state: any) => state.tokenLoader.token);
  //   const userId = useSelector((state: any) => state.tokenLoader.userId);
  const navigate = useNavigate();

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      const response = await fetch("http://localhost:6789/api/products/all");
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      const data: Product[] = await response.json();
      setProducts(data);
    } catch (error) {
      console.error("Error fetching products:", error);
    }
  };

  const handleSearchChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(event.target.value);
  };

  const filteredProducts = products.filter((product) =>
    product.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div>
      <Nav />
      <h1 style={{ textAlign: "center" }}>Product List</h1>
      <div className="search-bar">
        <input
          type="text"
          placeholder="Search by product name"
          value={searchTerm}
          onChange={handleSearchChange}
          className="search-input"
        />
      </div>
      <div className="app">
        <div className="product-list">
          {filteredProducts.map((product) => (
            <ProductCard key={product.productId} product={product} />
          ))}
        </div>
      </div>
    </div>
  );
};

export default Home;
