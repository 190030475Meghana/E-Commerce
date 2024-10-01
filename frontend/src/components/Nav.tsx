// import React, { useState } from "react";
// import { CgProfile } from "react-icons/cg";
// import { FaCartShopping } from "react-icons/fa6";
// import "../style/Home.css";
// import "../style/Nav.css";
// import { useNavigate } from "react-router-dom";

// const Nav = () => {
//   const [menuVisible, setMenuVisible] = useState(false);
//   const navigate = useNavigate();

//   const handleDropdownToggle = () => {
//     setMenuVisible(!menuVisible);
//   };

//   const handleCartClick = () => {
//     navigate("/cart");
//   };

//   const handleOrderHistory = () => {
//     navigate("/order-history");
//   };

//   const handleUpdateProfile = () => {
//     navigate("/update-profile");
//     setMenuVisible(false); // Hide dropdown after navigation
//   };

//   const handleLogout = () => {
//     navigate("/login");
//     setMenuVisible(false);
//   };

//   return (
//     <div>
//       <header className="home-header">
//         <div className="header-left">
//           <h2 className="hamburger-icon" onClick={() => navigate("/home")}>
//             ElectriMart
//           </h2>
//         </div>

//         <button className="order-history-button" onClick={handleOrderHistory}>
//           Order History
//         </button>

//         <div className="header-right">
//           <FaCartShopping
//             className="notification-icon"
//             onClick={handleCartClick}
//           />
//           <div className="profile-dropdown">
//             <CgProfile
//               className="profile-icon"
//               onClick={handleDropdownToggle}
//             />
//             {menuVisible && (
//               <div className="dropdown-content">
//                 <div className="dropdown-item" onClick={handleUpdateProfile}>
//                   Update Profile
//                 </div>
//                 <div className="dropdown-item" onClick={handleLogout}>
//                   Logout
//                 </div>
//               </div>
//             )}
//           </div>
//         </div>
//       </header>
//     </div>
//   );
// };

// export default Nav;

import React, { useState } from "react";
import { CgProfile } from "react-icons/cg";
import { FaCartShopping } from "react-icons/fa6";
import "../style/Home.css";
import "../style/Nav.css";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { clearSavedToken } from "../feature/redux/slice/tokenSlice.ts"; // Assuming clearToken is your action creator

const Nav = () => {
  const [menuVisible, setMenuVisible] = useState(false);
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const handleDropdownToggle = () => {
    setMenuVisible(!menuVisible);
  };

  const handleCartClick = () => {
    navigate("/cart");
  };

  const handleOrderHistory = () => {
    navigate("/order-history");
  };

  const handleUpdateProfile = () => {
    navigate("/update-profile");
    setMenuVisible(false); // Hide dropdown after navigation
  };

  const handleLogout = () => {
    const confirmLogout = window.confirm("Are you sure you want to logout?");
    if (confirmLogout) {
      // Dispatch action to clear token
      dispatch(clearSavedToken());

      // Navigate to login page
      navigate("/login");

      // Hide dropdown after navigation
      setMenuVisible(false);
    }
  };

  return (
    <div>
      <header className="home-header">
        <div className="header-left">
          <h2 className="hamburger-icon" onClick={() => navigate("/home")}>
            ElectriMart
          </h2>
        </div>

        <button className="order-history-button" onClick={handleOrderHistory}>
          Order History
        </button>

        <div className="header-right">
          <FaCartShopping
            className="notification-icon"
            onClick={handleCartClick}
          />
          <div className="profile-dropdown">
            <CgProfile
              className="profile-icon"
              onClick={handleDropdownToggle}
            />
            {menuVisible && (
              <div className="dropdown-content">
                <div className="dropdown-item" onClick={handleUpdateProfile}>
                  Update Profile
                </div>
                <div className="dropdown-item" onClick={handleLogout}>
                  Logout
                </div>
              </div>
            )}
          </div>
        </div>
      </header>
    </div>
  );
};

export default Nav;
