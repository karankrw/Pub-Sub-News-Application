import React from "react";
import { useState } from "react";

const Navbar = (props) => {
  const userId = props.userId;
  //Variable storing state of topic subscripttions
  const [isTech, setisTech] = useState(true);
  const [isSport, setisSport] = useState(true);
  const [isPoli, setisPoli] = useState(true);
  const [isEnt, setisEnt] = useState(true);

  //Funtion to inform the Broker if the user has subscribed or unsubscribed to Technology
  const handleTechChange = () => {
    setisTech(!isTech);
    if (isTech) {
      fetch(
        `http://localhost:8000/subscribe?name=${userId}&channel=technology`,
        {
          method: "GET",
        }
      )
        .then(console.log("Subscribing to Technology"))
        .catch((error) => {
          console.log(error);
        });
    } else {
      fetch(
        `http://localhost:8000/unsubscribe?name=${userId}&channel=technology`,
        {
          method: "GET",
        }
      )
        .then(console.log("Unsubscribing to Technology"))
        .catch((error) => {
          console.log(error);
        });
    }
  };

  //Funtion to inform the Broker if the user has subscribed or unsubscribed to Sports
  const handleSportChange = () => {
    setisSport(!isSport);
    if (isSport) {
      fetch(`http://localhost:8000/subscribe?name=${userId}&channel=sports`, {
        method: "GET",
      })
        .then(console.log("Subscribing to Sports"))
        .catch((error) => {
          console.log(error);
        });
    } else {
      fetch(`http://localhost:8000/unsubscribe?name=${userId}&channel=sports`, {
        method: "GET",
      })
        .then(console.log("Unsubscribing to Sports"))
        .catch((error) => {
          console.log(error);
        });
    }
  };

  //Funtion to inform the Broker if the user has subscribed or unsubscribed to Politics
  const handlePoliChange = () => {
    setisPoli(!isPoli);
    if (isPoli) {
      fetch(`http://localhost:8000/subscribe?name=${userId}&channel=politics`, {
        method: "GET",
      })
        .then(console.log("Subscribing to Politics"))
        .catch((error) => {
          console.log(error);
        });
    } else {
      fetch(
        `http://localhost:8000/unsubscribe?name=${userId}&channel=politics`,
        {
          method: "GET",
        }
      )
        .then(console.log("Unsubscribing to Politics"))
        .catch((error) => {
          console.log(error);
        });
    }
  };

  //Funtion to inform the Broker if the user has subscribed or unsubscribed to Technology
  const handleEntChange = () => {
    setisEnt(!isEnt);
    if (isEnt) {
      fetch(
        `http://localhost:8000/subscribe?name=${userId}&channel=entertainment`,
        {
          method: "GET",
        }
      )
        .then(console.log("Subscribing to Entertainment"))
        .catch((error) => {
          console.log(error);
        });
    } else {
      fetch(
        `http://localhost:8000/unsubscribe?name=${userId}&channel=entertainment`,
        {
          method: "GET",
        }
      )
        .then(console.log("Subscribing to Enterntainment"))
        .catch((error) => {
          console.log(error);
        });
    }
  };

  return (
    <div className="Navbar">
      <nav className="nav-extended">
        <div className="nav-wrapper">
          <a
            href="/"
            className="brand-logo"
            style={{ marginLeft: "43%", color: "white" }}
          >
            <i className="material-icons news-icon">newspaper</i>
            NewsApp
          </a>
          <a href="/" data-target="mobile-demo" className="sidenav-trigger">
            <i className="material-icons">menu</i>
          </a>
          <ul id="nav-mobile" className="right hide-on-med-and-down"></ul>
        </div>
        <form>
          <div className="nav-content" style={{ color: "#7FB5FF" }}>
            <ul
              className="tabs tabs-transparent"
              style={{ backgroundColor: "#7FB5FF", paddingLeft: "150px" }}
            >
              <li
                className="tab"
                style={{
                  fontSize: "1.2rem",
                  fontWeight: "bolder",
                  color: "black",
                }}
              >
                Technology
                <div
                  className="switch tab"
                  style={{ paddingLeft: "10px", paddingRight: "20px" }}
                >
                  <label style={{ color: "black" }}>
                    Unsub
                    <input
                      type="checkbox"
                      checked={!isTech}
                      onChange={() => handleTechChange()}
                    />
                    <span className="lever"></span>
                    Sub
                  </label>
                </div>
              </li>
              <li
                className="tab"
                style={{
                  fontSize: "1.2rem",
                  fontWeight: "bolder",
                  color: "black",
                }}
              >
                Sports
                <div
                  className="switch tab"
                  style={{ paddingLeft: "10px", paddingRight: "20px" }}
                >
                  <label style={{ color: "black" }}>
                    Unsub
                    <input
                      type="checkbox"
                      checked={!isSport}
                      onChange={() => handleSportChange()}
                    />
                    <span className="lever"></span>
                    Sub
                  </label>
                </div>
              </li>
              <li
                className="tab"
                style={{
                  fontSize: "1.2rem",
                  fontWeight: "bolder",
                  color: "black",
                }}
              >
                Politics
                <div
                  className="switch tab"
                  style={{ paddingLeft: "10px", paddingRight: "20px" }}
                >
                  <label style={{ color: "black" }}>
                    Unsub
                    <input
                      type="checkbox"
                      checked={!isPoli}
                      onChange={() => handlePoliChange()}
                    />
                    <span className="lever"></span>
                    Sub
                  </label>
                </div>
              </li>{" "}
              <li
                className="tab"
                style={{
                  fontSize: "1.2rem",
                  fontWeight: "bolder",
                  color: "black",
                }}
              >
                Entertainment
                <div
                  className="switch tab"
                  style={{ paddingLeft: "10px", paddingRight: "20px" }}
                >
                  <label style={{ color: "black" }}>
                    Unsub
                    <input
                      type="checkbox"
                      checked={!isEnt}
                      onChange={() => handleEntChange()}
                    />
                    <span className="lever"></span>
                    Sub
                  </label>
                </div>
              </li>
            </ul>
          </div>
        </form>
      </nav>

      <ul className="sidenav" id="mobile-demo">
        <li>
          <a href="sass.html">Sass</a>
        </li>
        <li>
          <a href="badges.html">Components</a>
        </li>
        <li>
          <a href="collapsible.html">JavaScript</a>
        </li>
      </ul>
    </div>
  );
};

export default Navbar;
