package com.example.projektsoftwarepraktikum.service;

import com.example.projektsoftwarepraktikum.entity.Benutzer;
import com.example.projektsoftwarepraktikum.entity.Negotiation;
import com.example.projektsoftwarepraktikum.entity.NegotiationMessage;
import com.example.projektsoftwarepraktikum.entity.Rolle;
import com.example.projektsoftwarepraktikum.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    public NegotiationMessage saveNegotiationMessage(NegotiationMessage message) {
        return messageRepository.save(message);
    }

    public List<NegotiationMessage> findAllNegotiationsMessages() {
        return messageRepository.findAll();
    }

    public NegotiationMessage saveNegotiationMessageData(final String[] data, Negotiation neg) {
        NegotiationMessage message = new NegotiationMessage();
        message.setNegotiation(neg);
        message.setSenderId(Integer.valueOf(data[1]));
        //für neue SenderID neuen User anlegen
        newUser(Integer.valueOf(data[1]));

        if (data[2] != "") {
            message.setSenderBestCase(Double.valueOf(data[2]));
        }
        if (data[3] != "") {
            message.setSenderWorstCase(Double.valueOf(data[3]));
        }
        if (data[4] != "") {
            message.setReceiversBestCase(Double.valueOf(data[4]));
        }
        if (data[5] != "") {
            message.setReceiversWorstCase(Double.valueOf(data[5]));
        }
        if (data[6] != "") {
            message.setSentDate(data[6]);
        }
        if (data[7] != "") {
            message.setMessageType(data[7]);
        }
        if (data[8] != "") {
            message.setNegoOutcome(data[8]);
        }
        if (data[9] != "") {
            message.setJointUtilityBest(Double.valueOf(data[9]));
        }
        if (data[10] != "") {
            message.setJointUtilityWorst(Double.valueOf(data[10]));
        }
        if (data[11] != "") {
            message.setContractImbalanceBest(Double.valueOf(data[11]));
        }
        if (data[12] != "") {
            message.setContractImbalanceWorst(Double.valueOf(data[12]));
        }
        if (data[13] != "") {
            message.setMessageCount(Integer.valueOf(data[13]));
        }
        if (data[14] != "") {
            message.setIssue5(data[14]);
        }
        if (data[15] != "") {
            message.setValue5(data[15]);
        }
        if (data[16] != "") {
            message.setUtility_Issue5(Double.valueOf(data[16]));
        }
        if (data[17] != "") {
            message.setIssue4(data[17]);
        }
        if (data[18] != "") {
            message.setValue4(data[18]);
        }
        if (data[19] != "") {
            message.setUtility_Issue4(Double.valueOf(data[19]));
        }
        if (data[20] != "") {
            message.setIssue2(data[20]);
        }
        if (data[21] != "") {
            message.setValue2(data[21]);
        }
        if (data[22] != "") {
            message.setUtility_Issue2(Double.valueOf(data[22]));
        }
        if (data[23] != "") {
            message.setIssue3(data[23]);
        }
        if (data[24] != "") {
            message.setValue3(data[24]);
        }
        if (data[25] != "") {
            message.setUtility_Issue3(Double.valueOf(data[25]));
        }
        if (!Objects.equals(data[7], "INIT")) {
            if (data[26] != "") {
                message.setIssue1(data[26]);
            }
            if (data[27] != "") {
                message.setValue1(data[27]);
            }
            if (data[28] != "") {
                message.setUtility_Issue1(Double.valueOf(data[28]));
            }
            if (data[29] != "") {
                message.setQ1(Integer.valueOf(data[29]));
            }
            if (data[30] != "") {
                message.setQ2(Integer.valueOf(data[30]));
            }
            if (data[31] != "") {
                message.setQ3(Integer.valueOf(data[31]));
            }
            if (data[32] != "") {
                message.setQ4(Integer.valueOf(data[32]));
            }
            if (data[33] != "") {
                message.setQ5(Integer.valueOf(data[33]));
            }
            if (data[34] != "") {
                message.setTKI_Competing(Integer.valueOf(data[34]));
            }
            if (data[35] != "") {
                message.setTKI_Compromising(Integer.valueOf(data[35]));
            }
            if (data[36] != "") {
                message.setTKI_Collaborating(Integer.valueOf(data[36]));
            }
            if (data[37] != "") {
                message.setTKI_Avoiding(Integer.valueOf(data[37]));
            }
            if (data[38] != "") {
                message.setTKI_Accommodating(Integer.valueOf(data[38]));
            }
            if (data[39] != "") {
                message.setTKI_Competing_Quantile_Abs(Integer.valueOf(data[39]));
            }
            if (data[40] != "") {
                message.setTKI_Compromising_Quantile_Abs(Integer.valueOf(data[40]));
            }
            if (data[41] != "") {
                message.setTKI_Avoiding_Quantile_Abs(Integer.valueOf(data[41]));
            }
            if (data[42] != "") {
                message.setTKI_Accommodating_Quantile_Abs(Integer.valueOf(data[42]));
            }
            if (data[43] != "") {
                message.setTKI_Competing_Quantile_Rel(Double.valueOf(data[43]));
            }
            if (data[44] != "") {
                message.setTKI_Compromising_Quantile_Rel(Double.valueOf(data[44]));
            }
            if (data[45] != "") {
                message.setTKI_Collaborating_Quantile_Rel(Double.valueOf(data[45]));
            }
            if (data[46] != "") {
                message.setTKI_Avoiding_Quantile_Rel(Double.valueOf(data[46]));
            }
            if (data[47] != "") {
                message.setTKI_Accommodating_Quantile_Rel(Double.valueOf(data[47]));
            }
        }
        neg.addMessage(message);
        return message;
    }

    private void newUser(Integer userID) {
        //Überprüfen ob bereits User existiert
        if (!userService.existingUser(userID)) {
            Benutzer newUser = new Benutzer();
            newUser.setUserId(userID);
            newUser.setUsername("user" + userID.toString());
            newUser.setPassword(passwordEncoder.encode("1234"));
            Rolle userRole = new Rolle("ROLE_USER");
            roleService.saveRole(userRole);

            Set<Rolle> userRoles = new HashSet<>();
            userRoles.add(userRole);
            newUser.setRoles(userRoles);
            newUser.setUserId(userID);
            userService.saveUser(newUser);
        }
    }
}