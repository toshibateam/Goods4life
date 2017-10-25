import React, { Component } from 'react';
import { View, TextInput, Text, TouchableOpacity, StyleSheet, Alert } from 'react-native';
import register from '../../api/register';

export default class SignUp extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            email: '',
            phone: '',
            addr: '',
            password: '',
            repassword: ''
        };
    }

    onSuccess() {
        Alert.alert(
            'Notice',
            'Sign up successfully',
            [
                { text: 'OK', onPress: this.props.gotoSignIn() }
            ],
            { cancelable: false }
        );
    }

    onFail() {
        Alert.alert(
            'Notice',
            'Register fail',
            [
                 { text: 'OK', onPress: () => this.removeEmail.bind(this) }
            ],
            { cancelable: false }
        );
    }

    removeEmail() {
        this.setState({ email: '' });
    }

    registerUser() {
        const { username, password, phone, email, addr } = this.state;
        const ws = register();
        ws.onopen = () => {
            // connection opened
            ws.send(JSON.stringify({UserName: username, PassWord: password, PhoneNumber: phone, Mail: email, Address: addr})); // send a message
        };
          
        ws.onmessage = (e) => {
            // a message was received
            console.log("messsage: " + e.data);
            if(e.data==200) return this.onSuccess();
            this.onFail();
        };
        
        ws.onerror = (e) => {
            // an error occurred
            console.log("error: " + e.message);
            this.onFail();
        };
        
        ws.onclose = (e) => {
            // connection closed
            console.log(e.code, e.reason);
            this.onFail();
        };
    }

    render() {
        const { inputStyle, bigButton, buttonText } = styles;
        return (
            <View>
                <TextInput 
                    style={inputStyle} 
                    placeholder="Enter your username" 
                    value={this.state.username}
                    onChangeText={text => this.setState({ username: text })}
                    underlineColorAndroid='transparent'
                    returnKeyType="next"
                    onSubmitEditing={()=> this.emailInput.focus()}
                />
                <TextInput 
                    style={inputStyle} 
                    placeholder="Enter your email" 
                    value={this.state.email}
                    onChangeText={text => this.setState({ email: text })}
                    returnKeyType="go"
                    ref={(input) => this.emailInput=input}
                    onSubmitEditing={()=> this.phoneInput.focus()}
                />
                <TextInput 
                    style={inputStyle} 
                    placeholder="Enter your phone number" 
                    value={this.state.phone}
                    onChangeText={text => this.setState({ phone: text })}
                    returnKeyType="go"
                    ref={(input) => this.phoneInput=input}
                    onSubmitEditing={()=> this.addressInput.focus()}
                />
                <TextInput 
                    style={inputStyle} 
                    placeholder="Enter your address" 
                    value={this.state.addr}
                    onChangeText={text => this.setState({ addr: text })}
                    returnKeyType="go"
                    ref={(input) => this.addressInput=input}
                    onSubmitEditing={()=> this.passwordInput.focus()}
                />
                <TextInput 
                    style={inputStyle} 
                    placeholder="Enter your password" 
                    value={this.state.password}
                    secureTextEntry
                    onChangeText={text => this.setState({ password: text })}
                    returnKeyType="go"
                    ref={(input) => this.passwordInput=input}
                    onSubmitEditing={()=> this.repasswordInput.focus()}
                />
                <TextInput 
                    style={inputStyle} 
                    placeholder="Re-enter your password" 
                    value={this.state.rePassword}
                    secureTextEntry
                    onChangeText={text => this.setState({ repassword: text })}
                    returnKeyType="go"
                    ref={(input) => this.repasswordInput=input}
                />
                <TouchableOpacity style={bigButton} onPress={this.registerUser.bind(this)}>
                    <Text style={buttonText}>SIGN UP NOW</Text>
                </TouchableOpacity>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    inputStyle: {
        height: 50,
        backgroundColor: '#fff',
        marginBottom: 10,
        borderRadius: 20,
        paddingLeft: 30
    },
    bigButton: {
        height: 50,
        borderRadius: 20,
        borderWidth: 1,
        borderColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center',
        marginTop: 10
    },
    buttonText: {
        fontFamily: 'Avenir',
        color: '#fff',
        fontWeight: '400'
    }
});
