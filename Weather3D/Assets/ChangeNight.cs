using UnityEngine;
using System.Collections;

public class ChangeNight : MonoBehaviour {

    public GameObject sun;
    public GameObject daytime;
    public GameObject night;

    private float targetX;
    private float sunTargetX;
    private bool moonInCamera = false;
    private Vector3 daytimePos;
    private Vector3 nightPos;

    // Use this for initialization
    void Start()
    {
        targetX = -8f;
        sunTargetX = -2.7f;
    }

    // Update is called once per frame
    void Update()
    {

        float dt = Time.deltaTime;
        if (Input.GetKey(KeyCode.Space) && !moonInCamera)
        {
            setMoonInCamera();
            daytimePos = daytime.transform.position;
            nightPos = night.transform.position;
        }

        /*if ((GetComponent<FollowHand>().GetHandClosed(1) || GetComponent<FollowHand>().GetHandClosed(0))
            && !moonInCamera)
        {
            setMoonInCamera();
            daytimePos = daytime.transform.position;
            nightPos = night.transform.position;
        }*/

        if (transform.position.x >= targetX && moonInCamera)
            transform.position = transform.position + Vector3.left * 6.0f * dt;       

        if (transform.position.x <= -7 && moonInCamera)
        {
            if (sun.transform.position.x <= sunTargetX)
                sun.transform.position = sun.transform.position + Vector3.right * 6.0f * dt;
            else
                moonInCamera = false;
        }
            

        if (transform.position.x <= -7 && moonInCamera)
        {
            daytime.transform.position = nightPos;
            night.transform.position = daytimePos;

        }

    }

    public void setMoonInCamera()
    {
        if (transform.position.x >= -6.5f)
            moonInCamera = true;
        else
            moonInCamera = false;
    }
}
