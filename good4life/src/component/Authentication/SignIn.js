import React, { Component } from 'react';
import { View, TextInput, Text, TouchableOpacity, StyleSheet } from 'react-native';
import {LoginButton, AccessToken, LoginManager, ShareDialog} from 'react-native-fbsdk';

import signIn from '../../api/signIn';
import global from '../global';

//import saveToken from '../../api/saveToken';

export default class SignIn extends Component {
    constructor(props) {
        super(props);
        this.state = {
            user: '',
            password: '',
            shareLinkContent: shareLinkContent,
            loggedFB: false,
        };

        const shareLinkContent = {
            contentType: 'link',
            contentUrl: 'https://www.facebook.com/',
          };
      
    }

    onSignInFB(){
        if(!this.state.loggedFB){
          LoginManager.logInWithPublishPermissions(['publish_actions'])
          .then((result)=>{
            if(result.isCancelled){
              alert('cancel login');
            }
              this.setState({loggedFB: true});
              AccessToken.getCurrentAccessToken().then(
                (data) => {
                  alert(data.accessToken.toString())
                }
              )
          })
          .catch(error=> console.log(error));
        }else{
          this.setState({loggedFB: false});
          LoginManager.logOut();
        }
        
      }

    // share link
    shareLinkWithShareDialog() {
        var tmp = this;
        ShareDialog.canShow(this.state.shareLinkContent)
        .then(function(canShow) {
            if (canShow) {
            return ShareDialog.show(tmp.state.shareLinkContent);
            }
        })
        .then(
            function(result) {
            if (result.isCancelled) {
                alert('Share cancelled');
            } else {
                alert('Share success');
            }
            },
            function(error) {
            alert('Share fail with error: ' + error);
            },
        );
    }

    onSignIn() {
        const { user, password } = this.state;
        const ws = signIn();
        ws.onopen = () => {
            // connection opened
            ws.send(JSON.stringify({Mail: user, PassWord: password})); // send a message
          };
          
          ws.onmessage = (e) => {
            // a message was received
            console.log("messsage: " + e.data);
            if(e.data == 200){
                global.onSignIn(user);
                this.props.goBackToMain();
               // saveToken(res.token);
            }
          };
          
          ws.onerror = (e) => {
            // an error occurred
            console.log("error: " + e.message);
          };
          
          ws.onclose = (e) => {
            // connection closed
            console.log(e.code, e.reason);
          };
        // signIn(user, password)
        //     .then(res => {
        //         global.onSignIn(res.user);
        //         this.props.goBackToMain();
        //         saveToken(res.token);
        //     })
        //     .catch(err => console.log(err));
    }

    gotoForgotPW(){
        const { navigator } = this.props;
        navigator.push('FORGOTPW');
    }

    render() {
        const { inputStyle, bigButton, buttonText, textContainer, text } = styles;
        const { user, password } = this.state;
        return (
            <View>
                <TextInput
                    style={inputStyle}
                    placeholder="Enter your userName"
                    value={user}
                    onChangeText={text => this.setState({ user: text })}
                    underlineColorAndroid='transparent'
                    returnKeyType="next"
                    onSubmitEditing={() => this.passwordInput.focus()}

                />
                <TextInput
                    style={inputStyle}
                    placeholder="Enter your password"
                    value={password}
                    onChangeText={text => this.setState({ password: text })}
                    secureTextEntry
                    returnKeyType="go"
                    ref={(input) => this.passwordInput = input}
                />

                <TouchableOpacity style={bigButton} onPress={this.onSignInFB.bind(this)}>
                    <Text style={buttonText}>{this.state.loggedFB ? "SIGN OUT FACEBOOK" : "SIGN IN FACEBOOK"}</Text>
                </TouchableOpacity>

                <TouchableOpacity style={bigButton} onPress={this.onSignIn.bind(this)}>
                    <Text style={buttonText}>SIGN IN NOW</Text>
                </TouchableOpacity>

                <TouchableOpacity style={styles.textContainer} onPress={this.gotoForgotPW.bind(this)}>
                                <Text style={styles.text}>Forgot password?</Text>
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
    },
    textContainer:{
        justifyContent: 'flex-end',
        alignItems: 'flex-end',
        marginTop: 10
    },
    text:{
        color: '#fff',
        fontWeight: '700',
    }
});
