const initData = () => (
    fetch('http://192.168.1.155:8080/api/')// eslint-disable-line
    .then(res => res.json())
);

export default initData;
