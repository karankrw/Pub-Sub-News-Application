import { useEffect, useState } from "react";

import React from "react";
import uuid from "react-uuid";

const Home = (props) => {
  const userId = props.userId;
  const itemKey = uuid();
  const [userData, setuserData] = useState([]);
  // console.log(userId);

  useEffect(() => {
    const interval = setInterval(() => {
      fetch(`http://localhost:8000/getData?name=${userId}&channel=none`, {
        method: "GET",
      })
        .then((response) => response.json())
        .then((data) => {
          if (data.length !== 0) {
            setuserData([...userData, ...data]);
          } else {
            setuserData([{ title: "No news to show" }]);
          }
        });
    }, 25000);

    return () => clearInterval(interval);
  }, [userId]);

  return (
    <div
      className="Home"
      style={{
        display: "block",
        justifyContent: "center",
        alignItems: "center",
        height: "100vh",
      }}
    >
      <div
        className="all-cards"
        style={{
          marginTop: "0px",
          paddingTop: "20px",
          paddingBottom: "20px",
          backgroundColor: "#112B3C",
        }}
      >
        {userData.map((item) => {
          return (
            <div
              className="mycard"
              style={{
                margin: "0 auto",
                maxWidth: "700px",
              }}
              key={itemKey}
            >
              <div className="card" style={{ backgroundColor: "#E8F9FD" }}>
                <div className="card-content">
                  <span className="card-title activator grey-text text-darken-4">
                    <p style={{ fontSize: "22px", fontWeight: 800 }}>
                      {item.title}
                    </p>
                    <p style={{ fontSize: "15px", color: "#413F42" }}>
                      {item.author === "Author details unavailable"
                        ? "Anonymous"
                        : item.author}{" "}
                      &nbsp; &nbsp; -{item.date}
                    </p>
                    <p
                      style={{
                        fontSize: "18px",
                        maxWidth: "600px",
                        fontWeight: 500,
                      }}
                    >
                      {item.description}
                    </p>
                  </span>
                  <p>
                    <a href={item.webUrl}>Read the article here</a>
                  </p>
                </div>
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
};

export default Home;
