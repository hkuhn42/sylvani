/**
 *
 */
package org.sylvani.oxford;

import java.util.Locale;

/**
 * @author hkuhn
 *
 *         <table>
 *         <thead>
 *         <tr>
 *         <th>Locale</th>
 *         <th>Gender</th>
 *         <th>Service Name Mapping</th>
 *         </tr>
 *         </thead>
 *         <tbody>
 *         <tr>
 *         <th rowspan="2">de-DE</th>
 *         <td>Female</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (de-DE, Hedda)"</td>
 *         </tr>
 *         <tr>
 *         <td>Male</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (de-DE, Stefan, Apollo)"</td>
 *         </tr>
 *         <tr>
 *         <th>en-AU</th>
 *         <td>Female</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (en-AU, Catherine)"</td>
 *         </tr>
 *         <tr>
 *         <th>en-CA</th>
 *         <td>Female</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (en-CA, Linda)"</td>
 *         </tr>
 *         <tr>
 *         <th rowspan="2">en-GB</th>
 *         <td>Female</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (en-GB, Susan, Apollo)"</td>
 *         </tr>
 *         <tr>
 *         <td>Male</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (en-GB, George, Apollo)"</td>
 *         </tr>
 *         <tr>
 *         <th>en-IN</th>
 *         <td>Male</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (en-IN, Ravi, Apollo)"</td>
 *         </tr>
 *         <tr>
 *         <th rowspan="2">en-US</th>
 *         <td>Female</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (en-US, ZiraRUS)"</td>
 *         </tr>
 *         <tr>
 *         <td>Male</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (en-US, BenjaminRUS)"</td>
 *         </tr>
 *         <tr>
 *         <th rowspan="2">es-ES</th>
 *         <td>Female</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (es-ES, Laura, Apollo)"</td>
 *         </tr>
 *         <tr>
 *         <td>Male</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (es-ES, Pablo, Apollo)"</td>
 *         </tr>
 *         <tr>
 *         <th>es-MX</th>
 *         <td>Male</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (es-MX, Raul, Apollo)"</td>
 *         </tr>
 *         <tr>
 *         <th>fr-CA</th>
 *         <td>Female</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (fr-CA, Caroline)"</td>
 *         </tr>
 *         <tr>
 *         <th rowspan="2">fr-FR</th>
 *         <td>Female</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (fr-FR, Julie, Apollo)"</td>
 *         </tr>
 *         <tr>
 *         <td>Male</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (fr-FR, Paul, Apollo)"</td>
 *         </tr>
 *         <tr>
 *         <th>it-IT</th>
 *         <td>Male</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (it-IT, Cosimo, Apollo)"</td>
 *         </tr>
 *         <tr>
 *         <th rowspan="2">ja-JP</th>
 *         <td>Female</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (ja-JP, Ayumi, Apollo)"</td>
 *         </tr>
 *         <tr>
 *         <td>Male</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (ja-JP, Ichiro, Apollo)"</td>
 *         </tr>
 *         <tr>
 *         <th>pt-BR</th>
 *         <td>Male</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (pt-BR, Daniel, Apollo)"</td>
 *         </tr>
 *         <tr>
 *         <th rowspan="2">ru-RU</th>
 *         <td>Female</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (ru-RU, Irina, Apollo)"</td>
 *         </tr>
 *         <tr>
 *         <td>Male</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (ru-RU, Pavel, Apollo)"</td>
 *         </tr>
 *         <tr>
 *         <th rowspan="3">zh-CN</th>
 *         <td>Female</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (zh-CN, HuihuiRUS)"</td>
 *         </tr>
 *         <tr>
 *         <td>Female</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (zh-CN, Yaoyao, Apollo)"</td>
 *         </tr>
 *         <tr>
 *         <td>Male</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (zh-CN, Kangkang, Apollo)"</td>
 *         </tr>
 *         <tr>
 *         <th rowspan="2">zh-HK</th>
 *         <td>Female</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (zh-HK, Tracy, Apollo)"</td>
 *         </tr>
 *         <tr>
 *         <td>Male</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (zh-HK, Danny, Apollo)"</td>
 *         </tr>
 *         <tr>
 *         <th rowspan="2">zh-TW</th>
 *         <td>Female</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (zh-TW, Yating, Apollo)"</td>
 *         </tr>
 *         <tr>
 *         <td>Male</td>
 *         <td>"Microsoft Server Speech Text to Speech Voice (zh-TW, Zhiwei, Apollo)"</td>
 *         </tr>
 *         </tbody>
 *         </table>
 *
 */
public enum Voice {

    HEDDA("Female", new Locale("de-DE"), "Hedda)");

    private String gender;
    private Locale locale;
    private String name;

    Voice(String gender, Locale locale, String name) {
        this.gender = gender;
        this.locale = locale;
        this.name = name;
    }

    // *<td>Female</td>*<td>"Microsoft Server Speech Text to Speech Voice (de-DE,
    // Hedda)"</td>*</tr>*<tr>*<td>Male</td>*<td>"Microsoft Server Speech Text to Speech Voice (de-DE, Stefan,
    // Apollo)"</td>*</tr>

    public String getMapping() {
        return "Microsoft Server Speech Text to Speech Voice (" + locale + ", " + name + ")";
    }

    public String getGender() {
        return gender;
    }
}
