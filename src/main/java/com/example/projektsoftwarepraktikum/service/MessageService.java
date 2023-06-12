package com.example.projektsoftwarepraktikum.service;

import com.example.projektsoftwarepraktikum.entity.Benutzer;
import com.example.projektsoftwarepraktikum.entity.Negotiation;
import com.example.projektsoftwarepraktikum.entity.NegotiationMessage;
import com.example.projektsoftwarepraktikum.entity.Rolle;
import com.example.projektsoftwarepraktikum.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

        if (data[2] != "" && !Objects.equals(data[2], "null")) {
            message.setSenderBestCase(Double.valueOf(data[2].replace(",",".")));
        }
        if (data[3] != ""&& !Objects.equals(data[3], "null")) {
            message.setSenderWorstCase(Double.valueOf(data[3].replace(",",".")));
        }
        if (data[4] != ""&& !Objects.equals(data[4], "null")) {
            message.setReceiversBestCase(Double.valueOf(data[4].replace(",",".")));
        }
        if (data[5] != ""&& !Objects.equals(data[5], "null")) {
            message.setReceiversWorstCase(Double.valueOf(data[5].replace(",",".")));
        }
        if (data[6] != "") {
            String[] datum = data[6].split(",");
            int month = monthToInt(datum[0].substring(0,3));
            int day = Integer.valueOf(datum[0].substring(4, datum[0].length()));
            int year = Integer.valueOf(datum[1].substring(2, 5))+2000;
            //je nachdem ob Stunde 1-Stellig (16) oder 2-Stellig (17)
            int hour, minute, second;
            if (datum[1].length() == 17) {
                 hour = hourAMPM(datum[1].substring(6, 8), datum[1].substring(15, 17));
                 minute = Integer.valueOf(datum[1].substring(9, 11));
                 second = Integer.valueOf(datum[1].substring(12, 14));
            } else {
                 hour = hourAMPM(datum[1].substring(6, 7), datum[1].substring(14, 16));
                 minute = Integer.valueOf(datum[1].substring(8, 10));
                 second = Integer.valueOf(datum[1].substring(11, 13));
            }

            LocalDateTime date = LocalDateTime.of(year, month, day, hour, minute, second);
            message.setSentDate(date);
        }
        if (data[7] != "") {
            message.setMessageType(data[7]);
        }
        if (data[8] != "") {
            message.setNegoOutcome(data[8]);
        }
        if (data[9] != ""&& !Objects.equals(data[9], "null")) {
            message.setJointUtilityBest(Double.valueOf(data[9].replace(",",".")));
        }
        if (data[10] != "" && !Objects.equals(data[10], "null")) {
            message.setJointUtilityWorst(Double.valueOf(data[10].replace(",",".")));
        }
        if (data[11] != "" && !Objects.equals(data[11], "null")) {
            message.setContractImbalanceBest(Double.valueOf(data[11].replace(",",".")));
        }
        if (data[12] != "" && !Objects.equals(data[12], "null")) {
            message.setContractImbalanceWorst(Double.valueOf(data[12].replace(",",".")));
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
        if (data[16] != "" && !Objects.equals(data[16], "null")) {
            message.setUtility_Issue5(Double.valueOf(data[16].replace(",",".")));
        }
        if (data[17] != "") {
            message.setIssue4(data[17]);
        }
        if (data[18] != "") {
            message.setValue4(data[18]);
        }
        if (data[19] != "" && !Objects.equals(data[19], "null")) {
            message.setUtility_Issue4(Double.valueOf(data[19].replace(",",".")));
        }
        if (data[20] != "") {
            message.setIssue2(data[20]);
        }
        if (data[21] != "") {
            message.setValue2(data[21]);
        }
        if (data[22] != "" && !Objects.equals(data[22], "null")) {
            message.setUtility_Issue2(Double.valueOf(data[22].replace(",",".")));
        }
        if (data[23] != "") {
            message.setIssue3(data[23]);
        }
        if (data[24] != "") {
            message.setValue3(data[24]);
        }
        if (data[25] != "" && !Objects.equals(data[25], "null")) {
            message.setUtility_Issue3(Double.valueOf(data[25].replace(",",".")));
        }
        if (!Objects.equals(data[7], "INIT")) {
            if (data[26] != "") {
                message.setIssue1(data[26]);
            }
            if (data[27] != "") {
                message.setValue1(data[27]);
            }
            if (data[28] != "" && !Objects.equals(data[28], "null")) {
                message.setUtility_Issue1(Double.valueOf(data[28].replace(",",".")));
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
            if (data[43] != "" && !Objects.equals(data[43], "null")) {
                message.setTKI_Competing_Quantile_Rel(Double.valueOf(data[43].replace(",",".")));
            }
            if (data[44] != "" && !Objects.equals(data[44], "null")) {
                message.setTKI_Compromising_Quantile_Rel(Double.valueOf(data[44].replace(",",".")));
            }
            if (data[45] != "" && !Objects.equals(data[45], "null")) {
                message.setTKI_Collaborating_Quantile_Rel(Double.valueOf(data[45].replace(",",".")));
            }
            if (data[46] != "" && !Objects.equals(data[46], "null")) {
                message.setTKI_Avoiding_Quantile_Rel(Double.valueOf(data[46].replace(",",".")));
            }
            if (data[47] != "" && !Objects.equals(data[47], "null")) {
                message.setTKI_Accommodating_Quantile_Rel(Double.valueOf(data[47].replace(",",".")));
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

    private int hourAMPM(String time, String format) {
        if (!Objects.equals(format, "PM")) {
            int hour = (Integer.valueOf(time)+12);
            if (hour == 24) {
                hour = 0;
            }
            return hour;
        } else {
            return Integer.valueOf(time);
        }
    }

    private int monthToInt(String month) {
        switch(month) {
            case "Dec":
                return 12;
            case "Nov":
                return 11;
            case "Oct":
                return 10;
            case "Sep":
                return 9;
            case "Aug":
                return 8;
            case "Jul":
                return 7;
            case "Jun":
                return 6;
            case "May":
                return 5;
            case "Apr":
                return 4;
            case "Mar":
                return 3;
            case "Feb":
                return 2;
            case "Jan":
                return 1;
        }
        return 0;
    }
}