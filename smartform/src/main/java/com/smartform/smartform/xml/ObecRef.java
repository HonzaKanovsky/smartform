package com.smartform.smartform.xml;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
class ObecRef {
    @XmlElement(name = "Kod", namespace = "urn:cz:isvs:ruian:schemas:ObecIntTypy:v1")
    private String kod;
}
